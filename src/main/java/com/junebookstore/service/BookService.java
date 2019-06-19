package com.junebookstore.service;

import com.junebookstore.entity.BookEntity;
import com.junebookstore.repository.BookBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookBaseRepository repository;

    public List<BookEntity> getBooks() {
        List<BookEntity> books = repository.getBooks();
        List<BookEntity> recommendation = repository.getBooksRecommendation()
                .stream()
                .peek(recommend -> recommend.setRecommended(true))
                .collect(Collectors.toList());

        List<BookEntity> booksWithoutRecommend = books.stream()
                .filter(book -> recommendation.stream().noneMatch(recommend -> recommend.equals(book)))
                .collect(Collectors.toList());

        ArrayList<BookEntity> normalWithRecommend = new ArrayList<>();
        normalWithRecommend.addAll(recommendation);
        normalWithRecommend.addAll(booksWithoutRecommend);

        return normalWithRecommend;
    }
}
