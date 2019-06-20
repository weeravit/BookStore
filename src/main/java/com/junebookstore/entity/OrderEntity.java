package com.junebookstore.entity;

import javax.persistence.*;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

    @ManyToOne
    @JoinColumn
    private BookEntity book;

    private boolean status;

    public OrderEntity() { }

    public OrderEntity(UserEntity user, BookEntity book) {
        this.user = user;
        this.book = book;
        this.status = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
