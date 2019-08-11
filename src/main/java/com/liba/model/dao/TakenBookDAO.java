package com.liba.model.dao;

import com.liba.model.entity.TakenBook;

import java.util.List;

public interface TakenBookDAO extends GenericDAO<TakenBook> {
    List<TakenBook> findAll();
}
