package com.junebookstore.controller;

import com.junebookstore.entity.BookEntity;
import com.junebookstore.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Books")
@RestController
public class BookController {
    private BookService service;

    public BookController(
            @Autowired BookService service
    ) {
        this.service = service;
    }

    @ApiOperation(value = "List of books", response = BookEntity.class, responseContainer = "List")
    @GetMapping("/books")
    public ResponseEntity<?> getBooks() {
        List<BookEntity> books = service.getBooks();
        return ResponseEntity.ok(books);
    }
}
