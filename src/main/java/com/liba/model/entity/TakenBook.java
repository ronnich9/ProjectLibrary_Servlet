package com.liba.model.entity;

import java.time.LocalDateTime;

public class TakenBook {

    private Long id;
    private User user;
    private Book book;
    private LocalDateTime taken_time;
    private LocalDateTime returned_time;

    public TakenBook() {
    }

    public TakenBook(Long id, User user, Book book, LocalDateTime taken_time, LocalDateTime returned_time) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.taken_time = taken_time;
        this.returned_time = returned_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getTaken_time() {
        return taken_time;
    }

    public void setTaken_time(LocalDateTime taken_time) {
        this.taken_time = taken_time;
    }

    public LocalDateTime getReturned_time() {
        return returned_time;
    }

    public void setReturned_time(LocalDateTime returned_time) {
        this.returned_time = returned_time;
    }
}
