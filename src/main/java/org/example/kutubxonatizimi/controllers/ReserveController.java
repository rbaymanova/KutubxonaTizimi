package org.example.kutubxonatizimi.controllers;

import org.example.kutubxonatizimi.entities.Reservation;
import org.example.kutubxonatizimi.entities.Users;
import org.example.kutubxonatizimi.serivce.ReserveService;
import org.example.kutubxonatizimi.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservations")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private UserService userService;

    @PostMapping("/reserve")
    public String reserveBook(@RequestParam Long adminId, @RequestParam Long bookId, @RequestParam Long userId ) {
        Users user = userService.getUserById(adminId, userId);
        reserveService.reserveBook(bookId, user);
        return "Book reserved successfully.";
    }

    @PostMapping("/accept")
    public String acceptOrder(@RequestParam Long adminId, @RequestParam Long reserveId) {
        reserveService.acceptOrder(adminId, reserveId);
        return "Book returned successfully.";
    }

    @PostMapping("/remove-expired")
    public String removeExpiredReservations(@RequestParam Long adminId) {
        reserveService.removeExpiredReservations(adminId);
        return "Expired reservations removed.";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long adminId, @RequestParam Long reserveId) {
        reserveService.borrowBook(adminId, reserveId);
        return "Book successfully marked as borrowed.";
    }

    @GetMapping("/penalty")
    public double calculatePenalty(@RequestParam Long adminId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                                   @RequestParam Long reservationId) {
        Reservation reservation = reserveService.getReservesById(adminId, reservationId);
        return reserveService.calculatePenalty(reservation, dueDate);
    }
}
