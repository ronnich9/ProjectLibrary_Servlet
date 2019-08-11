package com.liba.model.dao.imp;

import com.liba.model.dao.*;

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

    @Override
    public TakenBookDAO createTakenBookDao() {
        return new JDBCTakenBookDAO(getConnection());
    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
