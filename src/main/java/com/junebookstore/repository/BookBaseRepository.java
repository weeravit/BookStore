package com.junebookstore.repository;

import com.junebookstore.entity.BookEntity;

import java.util.List;

public interface BookBaseRepository {
    List<BookEntity> getBooks();
    List<BookEntity> getBooksRecommendation();
}
