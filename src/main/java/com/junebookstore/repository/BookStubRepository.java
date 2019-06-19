package com.junebookstore.repository;

import com.junebookstore.entity.BookEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookStubRepository implements BookBaseRepository {
    @Override
    public List<BookEntity> getBooks() {
        return Arrays.asList(
                new BookEntity(1, 300, "Before We Were Yours: A Novel", "Lisa Wingate", false),
                new BookEntity(2, 179, "When Never Comes", "Barbara Davis", false),
                new BookEntity(3, 200.5, "Giraffes Can't Dance", "Giles Andreae, Guy Parker-Rees", false),
                new BookEntity(4, 495, "The Great Alone: A Novel Kristin Hannah", "Kristin Hannah", false),
                new BookEntity(5, 149, "An American Princess: The Many Lives of Allene Tew", "Annejet van der Zijl, Michele Hutchison", false)
        );
    }

    @Override
    public List<BookEntity> getBooksRecommendation() {
        return Arrays.asList(
                new BookEntity(2, 179, "When Never Comes", "Barbara Davis", true),
                new BookEntity(5, 149, "An American Princess: The Many Lives of Allene Tew", "Annejet van der Zijl, Michele Hutchison", true)
        );
    }
}
