package com.junebookstore.business.entity;

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

    public OrderEntity() { }

    public OrderEntity(UserEntity user, BookEntity book) {
        this.user = user;
        this.book = book;
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
}
