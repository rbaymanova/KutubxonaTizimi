package org.example.kutubxonatizimi.repos;

import org.example.kutubxonatizimi.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {
    List<Rating> findByBookId(Long book_id);
}
