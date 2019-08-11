package com.liba.model.dao;

import com.liba.model.entity.Author;
import com.liba.model.entity.Book;

import java.util.List;

public interface BookDAO extends GenericDAO<Book>{
    Book findByTitle(String title);
    List<Book> findAll();
    void delete(Long bookId);
}
