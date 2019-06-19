package com.junebookstore.controller;

import com.junebookstore.model.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> login(@RequestBody Login login) {
        return ResponseEntity.ok(login);
    }

    @PostMapping("/users/orders")
    public ResponseEntity<?> orderBooks() {
        return ResponseEntity.ok("");
    }
}
