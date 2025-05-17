package org.example.kutubxonatizimi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int price_day;
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Rating> ratings;
    @Enumerated(EnumType.STRING)
    private BookStatus is_available;
}
