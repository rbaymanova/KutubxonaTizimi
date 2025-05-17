package org.example.kutubxonatizimi.serivce;

import lombok.RequiredArgsConstructor;
import org.example.kutubxonatizimi.entities.Book;
import org.example.kutubxonatizimi.entities.Rating;
import org.example.kutubxonatizimi.repos.BookRepo;
import org.example.kutubxonatizimi.repos.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepo.getBookById(id);
    }
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }
    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }

}
