package com.junebookstore.service;

import com.junebookstore.entity.BookEntity;
import com.junebookstore.repository.BookBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookBaseRepository repository;

    public List<BookEntity> getBooks() {
        return repository.getBooks();
    }
}
