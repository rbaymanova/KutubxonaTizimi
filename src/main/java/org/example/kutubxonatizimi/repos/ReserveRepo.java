package org.example.kutubxonatizimi.repos;

import jakarta.persistence.Entity;
import org.example.kutubxonatizimi.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepo extends JpaRepository<Reservation, Long> {}
