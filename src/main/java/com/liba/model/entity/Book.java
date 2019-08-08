package com.liba.model.entity;

public class Book {

    private Long id;
    private String title;
    private Author author;
    private int pages;
    private int year;
    private String imgUrl;
    private int amount;


    public Book() {
    }

    public Book(Long id, String title, Author author, int pages, int year, String imgUrl, int amount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
        this.imgUrl = imgUrl;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
