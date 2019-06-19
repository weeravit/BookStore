package com.junebookstore.controller;

import com.junebookstore.entity.BookEntity;
import com.junebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService service;

    @GetMapping("/books")
    public ResponseEntity<?> getBooks() {
        List<BookEntity> books = service.getBooks();
        return ResponseEntity.ok(books);
    }
}
