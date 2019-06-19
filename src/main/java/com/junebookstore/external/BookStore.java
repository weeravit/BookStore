package com.junebookstore.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junebookstore.model.Book;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private final String BASE_URL = "https://scb-test-book-publisher.herokuapp.com";

    private ObjectMapper objectMapper = new ObjectMapper();
    private RestTemplate restTemplate = new RestTemplate();

    private static BookStore ourInstance = new BookStore();

    public static BookStore getInstance() {
        return ourInstance;
    }

    private BookStore() {
    }

    public List<Book> getBooks() {
        String url = String.format("%s/books", BASE_URL);
        String json = restTemplate.getForObject(url, String.class);

        try {
            return objectMapper.readValue(json, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Book> getBooksRecommendation() {
        String url = String.format("%s/books/recommendation", BASE_URL);
        String json = restTemplate.getForObject(url, String.class);

        try {
            return objectMapper.readValue(json, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
