package com.yk.librarybook.model;

public class Book {
    int id;
    String name;
    String author;
    int count;
    double price;
    int originalCount;

    public int getOriginalCount() {
        return originalCount;
    }

    public void setOriginalCount(int originalCount) {
        this.originalCount = originalCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
