package com.junebookstore.business.service;

import com.junebookstore.business.entity.BookEntity;
import com.junebookstore.gateway.BookStoreGateway;
import com.junebookstore.business.repository.BookRepository;
import com.junebookstore.common.transform.BookTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository repository;
    private BookStoreGateway bookStoreGateway;

    public BookService(
            @Autowired BookRepository repository,
            @Autowired BookStoreGateway bookStoreGateway
    ) {
        this.repository = repository;
        this.bookStoreGateway = bookStoreGateway;
    }

    public List<BookEntity> getBooks() {
        return repository.findAll(
                Sort.by(Sort.Direction.DESC, BookEntity.IS_RECOMMENDED_COL)
        );
    }

    public List<BookEntity> cacheBooks() {
        List<BookEntity> booksList = BookTransform.toEntity(
                bookStoreGateway.getBooks(),
                false
        );

        List<BookEntity> booksRecommendationList = BookTransform.toEntity(
                bookStoreGateway.getBooksRecommendation(),
                true
        );

        repository.deleteAll();
        repository.saveAll(booksList);
        repository.saveAll(booksRecommendationList);

        return repository.findAll(Sort.by(Sort.Direction.DESC, BookEntity.IS_RECOMMENDED_COL));
    }
}
