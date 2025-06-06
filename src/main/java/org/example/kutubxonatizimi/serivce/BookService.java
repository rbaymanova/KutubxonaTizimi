package org.example.kutubxonatizimi.serivce;

import org.example.kutubxonatizimi.entities.Book;
import org.example.kutubxonatizimi.entities.BookStatus;
import org.example.kutubxonatizimi.entities.Users;
import org.example.kutubxonatizimi.repos.BookRepo;
import org.example.kutubxonatizimi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private final BookRepo bookRepo;
    @Autowired
    private final UserRepo userRepo;

    public BookService(BookRepo bookRepo, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepo.getBookById(id);
    }
    public Book addBook(Long adminId, Book book) {
        Users user = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(user);

        return bookRepo.save(book);
    }
    public Book updateBook(Long adminId, Long bookId, Book bookDetails) {
        Users user = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(user);

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice_day(bookDetails.getPrice_day());
        book.setIs_available(bookDetails.getIs_available());

        return bookRepo.save(book);
    }

    public Book partialUpdateBook(Long adminId, Long bookId,
                                  String title,
                                  String author,
                                  Integer price_day,
                                  BookStatus is_available) {
        Users user = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(user);

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (title != null) book.setTitle(title);
        if (author != null) book.setAuthor(author);
        if (price_day != null) book.setPrice_day(price_day);
        if (is_available != null) book.setIs_available(is_available);

        return bookRepo.save(book);
    }

    public void deleteBookById(Long adminId, Long bookId) {
        Users user = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdminOrOperator(user);

        if (!bookRepo.existsById(bookId)) {
            throw new IllegalArgumentException("Book not found");
        }

        bookRepo.deleteById(bookId);
    }
}
