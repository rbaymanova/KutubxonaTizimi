package org.example.kutubxonatizimi.controllers;

import org.example.kutubxonatizimi.entities.Users;
import org.example.kutubxonatizimi.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers(@RequestParam Long adminId) {
        return userService.getAllUsers(adminId);
    }

    @GetMapping("/{userId}")
    public Users getUserById(@RequestParam Long adminId, @PathVariable Long userId) {
        return userService.getUserById(adminId, userId);
    }

    @PostMapping("/add")
    public Users addUser(@RequestParam Long adminId, @RequestBody Users user) {
        return userService.addUser(adminId, user);
    }

    @PutMapping("/update/{userId}")
    public Users updateUser(@RequestParam Long adminId, @PathVariable Long userId, @RequestBody Users updatedUser) {
        return userService.updateUser(adminId, userId, updatedUser);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@RequestParam Long adminId, @PathVariable Long userId) {
        userService.deleteUserById(adminId, userId);
    }
}
