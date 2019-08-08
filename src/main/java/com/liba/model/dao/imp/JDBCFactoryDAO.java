package com.liba.model.dao.imp;

import com.liba.model.dao.AuthorDAO;
import com.liba.model.dao.BookDAO;
import com.liba.model.dao.FactoryDAO;
import com.liba.model.dao.UserDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCFactoryDAO extends FactoryDAO {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDAO createUserDao() {
        return new JDBCUserDAO(getConnection());
    }

    @Override
    public AuthorDAO createAuthorDao() {
        return new JDBCAuthorDAO(getConnection());
    }

    @Override
    public BookDAO createBookDao() {
        return new JDBCBookDAO(getConnection());
    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
