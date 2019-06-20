package com.junebookstore.model;

public class OrderPrice {
    private double price;

    public OrderPrice() {}

    public OrderPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
