package com.liba.model.dao.imp;

import com.liba.model.dao.AuthorDAO;
import com.liba.model.dao.mapper.AuthorMapper;
import com.liba.model.entity.Author;

import java.sql.*;
import java.util.*;

public class JDBCAuthorDAO implements AuthorDAO {
    private Connection connection;


    public JDBCAuthorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Author findByName(String name) {
        AuthorMapper authorMapper = new AuthorMapper();
        try (PreparedStatement ps =
                     connection.prepareStatement("select * from authors where name =?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return authorMapper.extractFromResultSet(rs);
            }
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Author entity) {

        try (PreparedStatement ps =
                     connection.prepareStatement("insert into authors(name) values (?)")) {
            ps.setString(1, entity.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        AuthorMapper authorMapper = new AuthorMapper();

        try (PreparedStatement ps =
                     connection.prepareStatement("select * from authors where id =?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Author author;
                author = authorMapper.extractFromResultSet(rs);
                return Optional.of(author);
            }
            else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Author> findAll() {

        Map<Long, Author> authors = new HashMap<>();

        final String query = "select * from authors";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            AuthorMapper authorMapper = new AuthorMapper();
            while (rs.next()) {
                Author author = authorMapper
                        .extractFromResultSet(rs);
                authorMapper.makeUnique(authors, author, rs);
            }
            return new ArrayList<>(authors.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void delete(Long authorId) {
        try (PreparedStatement ps = connection.prepareStatement("delete from authors where id = ?")) {
            ps.setLong(1, authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
