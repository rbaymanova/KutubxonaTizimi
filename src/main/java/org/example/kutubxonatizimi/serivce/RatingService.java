package org.example.kutubxonatizimi.serivce;

import org.example.kutubxonatizimi.entities.Book;
import org.example.kutubxonatizimi.entities.Rating;
import org.example.kutubxonatizimi.entities.Users;
import org.example.kutubxonatizimi.repos.BookRepo;
import org.example.kutubxonatizimi.repos.RatingRepo;
import org.example.kutubxonatizimi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private final RatingRepo repo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final BookRepo bookRepo;

    public RatingService(RatingRepo repo, UserRepo userRepo, BookRepo bookRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }


    public Rating addRating(Long user_id, Long book_id, int score) {
        if (score < 0 || score > 5) {
            throw new IllegalArgumentException("score must be between 0 and 5");
        }
        Users users = userRepo.findById(user_id).orElseThrow(() -> new IllegalArgumentException("user not found"));
        Book book = bookRepo.findById(book_id).orElseThrow(() -> new IllegalArgumentException("book not found"));

        Rating rating = new Rating();
        rating.setUsers(users);
        rating.setBook(book);
        rating.setScore(score);

        return repo.save(rating);
    }

    public double getAvgRating(Long book_id) {
        List<Rating> ratings = repo.findByBookId(book_id);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getScore();
        }
        return sum / ratings.size();
    }
}
