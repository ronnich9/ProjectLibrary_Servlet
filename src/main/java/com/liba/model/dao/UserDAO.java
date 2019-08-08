package com.liba.model.dao;

import com.liba.model.entity.User;

public interface UserDAO extends GenericDAO<User> {

    User findByUsername(String username);
}
