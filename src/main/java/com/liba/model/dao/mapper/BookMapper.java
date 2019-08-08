package com.liba.model.dao.mapper;

import com.liba.model.entity.Author;
import com.liba.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BookMapper implements ObjectMapper<Book> {
    @Override
    public Book extractFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        Author author = new Author();

        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        author.setId(rs.getLong("author_id"));
        book.setAuthor(author);
        book.setPages(rs.getInt("pages"));
        book.setYear(rs.getInt("year"));
        book.setImgUrl(rs.getString("img_url"));
        book.setAmount(rs.getInt("amount"));

        return book;
    }

    @Override
    public Book makeUnique(Map<Long, Book> cache, Book book, ResultSet rs) throws SQLException {

        cache.putIfAbsent(book.getId(), book);
        return book;
    }


}
