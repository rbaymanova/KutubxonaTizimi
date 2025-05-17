package org.example.kutubxonatizimi.controllers;

import org.example.kutubxonatizimi.entities.Book;
import org.example.kutubxonatizimi.entities.BookStatus;
import org.example.kutubxonatizimi.serivce.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

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
            @RequestParam Long user_id,
            @RequestBody Book book) {
        return bookService.addBook(user_id, book);
    }


    @PutMapping("/update/{bookId}")
    public Book updateBook(
            @RequestParam Long user_id,
            @PathVariable Long book_id,
            @RequestBody Book bookDetails, @PathVariable String book_id) {
        return bookService.updateBook(user_id, book_id, bookDetails);
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
            @RequestParam Long user_id,
            @PathVariable Long bookId) {
        bookService.deleteBookById(user_id, bookId);
    }

}
