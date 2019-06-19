package com.junebookstore.service;

import com.junebookstore.entity.BookEntity;
import com.junebookstore.external.BookStore;
import com.junebookstore.repository.BookRepository;
import com.junebookstore.transform.BookTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    public List<BookEntity> getBooks() {
        List<BookEntity> booksList = BookTransform.toEntity(
                BookStore.getInstance().getBooks(),
                false
        );

        List<BookEntity> booksRecommendationList = BookTransform.toEntity(
                BookStore.getInstance().getBooksRecommendation(),
                true
        );

        repository.deleteAll();
        repository.saveAll(booksList);
        repository.saveAll(booksRecommendationList);

        return repository.findAll(Sort.by(Sort.Direction.DESC, "isRecommended"));
    }
}
