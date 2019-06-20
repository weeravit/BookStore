package com.junebookstore.controller;

import com.junebookstore.model.OrderBooks;
import com.junebookstore.model.Register;
import com.junebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/users")
    public ResponseEntity<?> createUserAccount(@RequestBody Register request) {
        int userId = service.register(request);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserInformation(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUserActivity() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/users/orders")
    public ResponseEntity<?> orderBooks(@RequestBody OrderBooks request) {
        return ResponseEntity.ok("");
    }
}
