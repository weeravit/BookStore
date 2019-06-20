package com.junebookstore.controller;

import com.junebookstore.model.OrderBooks;
import com.junebookstore.model.OrderPrice;
import com.junebookstore.model.Register;
import com.junebookstore.model.UserInformation;
import com.junebookstore.service.OrderService;
import com.junebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @PostMapping("/users")
    public ResponseEntity<?> createUserAccount(@RequestBody Register request) {
        userService.register(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserInformation(Principal principal) {
        UserInformation userInformation = userService.getInformation(principal.getName());
        return ResponseEntity.ok(userInformation);
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUserActivity(Principal principal) {
        orderService.deleteOrderHistory(principal.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/orders")
    public ResponseEntity<?> orderBooks(Principal principal, @RequestBody OrderBooks request) {
        OrderPrice orderPrice = orderService.orderBooks(principal.getName(), request);
        return ResponseEntity.ok(orderPrice);
    }
}
