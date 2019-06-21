package com.junebookstore.common.model;

import java.util.List;

public class UserInformation {
    private String name;
    private String surname;
    private String dateOfBirth;
    private List<Integer> books;

    public UserInformation() { }

    public UserInformation(String name, String surname, String dateOfBirth, List<Integer> books) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Integer> getBooks() {
        return books;
    }

    public void setBooks(List<Integer> books) {
        this.books = books;
    }
}
