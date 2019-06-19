package com.junebookstore.service;

import com.junebookstore.entity.BookEntity;
import com.junebookstore.external.BookStore;
import com.junebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    public List<BookEntity> getBooks() {
        return BookStore.getInstance().getBooksRecommendation();
    }
}
