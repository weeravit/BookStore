package com.junebookstore.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.junebookstore.helper.JsonWrapper;
import com.junebookstore.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("BookStoreGateway")
public class BookStoreGateway {
    private final String BASE_URL = "https://scb-test-book-publisher.herokuapp.com";

    private JsonWrapper jsonWrapper = new JsonWrapper();
    private RestTemplate restTemplate = new RestTemplate();

    public BookStoreGateway() {}

    public List<Book> getBooks() {
        String url = String.format("%s/books", BASE_URL);
        String json = restTemplate.getForObject(url, String.class);

        try {
            return jsonWrapper.readValue(json, new TypeReference<List<Book>>() {
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
            return jsonWrapper.readValue(json, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
