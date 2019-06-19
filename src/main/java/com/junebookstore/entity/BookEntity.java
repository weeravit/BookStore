package com.junebookstore.entity;

public class BookEntity {
    private int id;
    private double price;
    private String bookName;
    private String authorName;
    private boolean isRecommended;

    public BookEntity(int id, double price, String bookName, String authorName, boolean isRecommended) {
        this.id = id;
        this.price = price;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isRecommended = isRecommended;
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

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }
}