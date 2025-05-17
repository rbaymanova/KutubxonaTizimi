package org.example.kutubxonatizimi.controllers;

import org.example.kutubxonatizimi.entities.Book;
import org.example.kutubxonatizimi.entities.BookStatus;
import org.example.kutubxonatizimi.entities.Rating;
import org.example.kutubxonatizimi.serivce.BookService;
import org.example.kutubxonatizimi.serivce.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/add")
    public Book addBook(
            @RequestParam Long userId,
            @RequestBody Book book) {
        return bookService.addBook(userId, book);
    }


    @PutMapping("/update/{bookId}")
    public Book updateBook(
            @RequestParam Long userId,
            @PathVariable Long bookId,
            @RequestBody Book bookDetails) {
        return bookService.updateBook(userId, bookId, bookDetails);
    }

    @PatchMapping("/update/{bookId}")
    public Book partialUpdateBook(
            @RequestParam Long userId,
            @PathVariable Long bookId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer day_price,
            @RequestParam(required = false) BookStatus is_available) {
        return bookService.partialUpdateBook(userId, bookId, name, author, day_price, is_available);
    }

    @DeleteMapping("/delete/{bookId}")
    public void deleteBook(
            @RequestParam Long userId,
            @PathVariable Long bookId) {
        bookService.deleteBookById(userId, bookId);
    }

    @PostMapping("/rating/add/{bookId}")
    public Rating addRating(@PathVariable Long bookId,
                            @RequestParam Long userId,
                            @RequestParam int score) {
        return ratingService.addRating(userId, bookId, score);
    }

    @GetMapping("/rating/{bookId}")
    public double getAverageRating(@PathVariable Long bookId) {
        return ratingService.getAvgRating(bookId);
    }

}
