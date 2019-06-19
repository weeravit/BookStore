package com.junebookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/users")
    public ResponseEntity<?> createUserAccount() {
        return ResponseEntity.ok("");
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserInformation() {
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUserActivity() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/users/orders")
    public ResponseEntity<?> orderBooks() {
        return ResponseEntity.ok("");
    }
}
