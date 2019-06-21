package com.junebookstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    @JsonProperty("id")
    private int id;
    @JsonProperty("price")
    private double price;
    @JsonProperty("book_name")
    private String bookName;
    @JsonProperty("author_name")
    private String authorName;

    public Book() {
    }

    public Book(int id, double price, String bookName, String authorName) {
        this.id = id;
        this.price = price;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}