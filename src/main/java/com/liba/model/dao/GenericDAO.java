package com.liba.model.dao;

import java.util.Optional;

public interface GenericDAO<T> extends AutoCloseable{
    void create (T entity);
    Optional<T> findById(Long id);
    void close();

}
