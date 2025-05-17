package org.example.kutubxonatizimi.repos;

import org.example.kutubxonatizimi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Book getBookById(Long id);
}
