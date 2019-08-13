package com.liba.model.entity;

import java.util.List;
import java.util.Set;

public class User {



    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Set<Role> role;
    private List<TakenBook> takenBooks;

    public User() {
    }

    public User(String username, String password,String phone, String email, Set<Role> role) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public User(Long id, String username, String password, String phone, String email, Set<Role> role, List<TakenBook> takenBooks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.takenBooks = takenBooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public List<TakenBook> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<TakenBook> takenBooks) {
        this.takenBooks = takenBooks;
    }

}
