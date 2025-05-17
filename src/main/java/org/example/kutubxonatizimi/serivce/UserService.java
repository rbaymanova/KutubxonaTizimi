package org.example.kutubxonatizimi.serivce;

import org.example.kutubxonatizimi.entities.Users;
import org.example.kutubxonatizimi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }
    public Users getUserById(Long id) {
        return userRepo.getUsersById(id);
    }
    public Users addUser(Users users) {
        return userRepo.save(users);
    }
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }
}
