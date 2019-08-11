package com.liba.model.dao;

import com.liba.model.entity.Author;

import java.util.List;

public interface AuthorDAO extends GenericDAO<Author> {
    Author findByName(String name);
    List<Author> findAll();
    void delete(Long authorId);
}

