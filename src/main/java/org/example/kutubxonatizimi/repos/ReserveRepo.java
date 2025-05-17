package org.example.kutubxonatizimi.repos;

import org.example.kutubxonatizimi.entities.BookStatus;
import org.example.kutubxonatizimi.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepo extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(BookStatus status);
}
