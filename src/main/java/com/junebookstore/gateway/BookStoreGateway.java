package com.junebookstore.gateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.junebookstore.common.wrapper.JsonWrapper;
import com.junebookstore.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookStoreGateway {
    @Value("${external.books.url}")
    private String booksUrl;

    @Value("${external.books-recommend.url}")
    private String booksRecommendUrl;

    private JsonWrapper jsonWrapper = new JsonWrapper();
    private RestTemplate restTemplate = new RestTemplate();

    public BookStoreGateway() { }

    public List<Book> getBooks() {
        String json = restTemplate.getForObject(booksUrl, String.class);

        try {
            return jsonWrapper.readValue(json, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Book> getBooksRecommendation() {
        String json = restTemplate.getForObject(booksRecommendUrl, String.class);

        try {
            return jsonWrapper.readValue(json, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
