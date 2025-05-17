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

    public List<Users> getAllUsers(Long adminId) {
        Users requester = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdmin(requester);
        return userRepo.findAll();
    }

    public Users getUserById(Long adminId, Long userId) {
        Users requester = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CheckRole.requireAdmin(requester);

        return userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Target user not found"));
    }

    public Users addUser(Long adminId, Users newUser) {
        Users admin = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin user not found"));
        CheckRole.requireAdmin(admin);

        return userRepo.save(newUser);
    }

    public void deleteUserById(Long adminId, Long userId) {
        Users admin = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin user not found"));
        CheckRole.requireAdmin(admin);

        userRepo.deleteById(userId);
    }

    public Users updateUser(Long adminId, Long userId, Users updatedDetails) {
        Users admin = userRepo.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin user not found"));
        CheckRole.requireAdmin(admin);

        Users existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User to update not found"));

        existingUser.setName(updatedDetails.getName());
        existingUser.setRoles(updatedDetails.getRoles());

        return userRepo.save(existingUser);
    }
}
