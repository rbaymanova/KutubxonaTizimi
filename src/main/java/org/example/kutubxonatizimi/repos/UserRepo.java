package org.example.kutubxonatizimi.repos;

import org.example.kutubxonatizimi.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users getUsersById(Long id);
}
