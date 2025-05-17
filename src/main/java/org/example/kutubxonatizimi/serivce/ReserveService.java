package org.example.kutubxonatizimi.serivce;

import org.example.kutubxonatizimi.entities.*;
import org.example.kutubxonatizimi.repos.BookRepo;
import org.example.kutubxonatizimi.repos.ReserveRepo;
import org.example.kutubxonatizimi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ReserveService {
    @Autowired
    private final ReserveRepo reserveRepo;
    @Autowired
    private final BookRepo bookRepo;
    @Autowired
    private final UserRepo userRepo;

    public ReserveService(ReserveRepo reserveRepo, BookRepo bookRepo, UserRepo userRepo) {
        this.reserveRepo = reserveRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public Reservation getReservesById(Long adminId, Long userId) {
        Users requester = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(requester);

        return reserveRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Target user not found"));
    }

    public void acceptOrder(Long reserveId, Long adminId) {
        Users user = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(user);

        Reservation reservation = reserveRepo.findById(reserveId).orElseThrow();
        reservation.setStatus(BookStatus.RETURNED);
        reservation.setReturned_date(LocalDate.now());
        reserveRepo.save(reservation);
    }


    public void reserveBook(Long bookId, Users users) {

        Book book = bookRepo.findById(bookId).orElseThrow();
        if (book.getIs_available() == BookStatus.RESERVED)
            throw new IllegalStateException("Already reserved");

        Reservation order = new Reservation();
        order.setUsers(users);
        order.setBook(book);
        order.setReserved_date(LocalDate.now());
        order.setStatus(BookStatus.RESERVED);
        reserveRepo.save(order);
    }

    public void removeExpiredReservations(Long adminId) {
        Users user = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(user);
        List<Reservation> reservedOrders = reserveRepo.findByStatus(BookStatus.RESERVED);
        for (Reservation reservation : reservedOrders) {
            if (reservation.getReserved_date().isBefore(LocalDate.now())) {
                reservation.setStatus(BookStatus.CANCELLED);
                reservation.getBook().setIs_available(BookStatus.CANCELLED);
                reserveRepo.save(reservation);
            }
        }
    }

    public void borrowBook(Long adminId, Long reserveId) {
        Users admin = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(admin);

        Reservation reservation = reserveRepo.findById(reserveId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatus() != BookStatus.RESERVED && reservation.getStatus() != BookStatus.RETURNED) {
            throw new IllegalStateException("Only reserved or returned books can be borrowed.");
        }

        reservation.setBorrowed_date(LocalDate.now());
        reservation.setDue_date(LocalDate.now().plusDays(7));
        reservation.setStatus(BookStatus.BORROWED);
        reservation.getBook().setIs_available(BookStatus.BORROWED);

        reserveRepo.save(reservation);
    }


    public double calculatePenalty(Reservation reservation, LocalDate due_date) {
        long usedDays = Math.abs(due_date.toEpochDay() - reservation.getBorrowed_date().toEpochDay());
        int allowedDays = 7; //7 days
        if (usedDays <= allowedDays) return 0;
        long lateDays = usedDays - allowedDays;
        double dailyRate = reservation.getBook().getPrice_day();
        return lateDays * dailyRate * 0.01;
    }


}
