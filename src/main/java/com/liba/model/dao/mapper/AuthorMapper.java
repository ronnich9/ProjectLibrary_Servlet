package com.liba.model.dao.mapper;

import com.liba.model.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AuthorMapper implements ObjectMapper<Author>{
    @Override
    public Author extractFromResultSet(ResultSet rs) throws SQLException {
        return new Author(rs.getLong("id"),
                rs.getString("name"));

    }

    @Override
    public Author makeUnique(Map<Long, Author> cache, Author author, ResultSet rs) throws SQLException {
        cache.putIfAbsent(author.getId(), author);
        return author;
    }
}
