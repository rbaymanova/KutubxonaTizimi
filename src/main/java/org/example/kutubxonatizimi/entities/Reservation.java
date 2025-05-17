package org.example.kutubxonatizimi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private LocalDate reserved_date;
    private LocalDate borrowed_date;
    private LocalDate due_date;
    private LocalDate returned_date;
    @Enumerated(EnumType.STRING)
    private BookStatus status;

}
