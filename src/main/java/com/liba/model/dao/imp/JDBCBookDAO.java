package com.liba.model.dao.imp;

import com.liba.model.dao.BookDAO;
import com.liba.model.dao.mapper.AuthorMapper;
import com.liba.model.dao.mapper.BookMapper;
import com.liba.model.entity.Author;
import com.liba.model.entity.Book;

import java.sql.*;
import java.util.*;

public class JDBCBookDAO implements BookDAO {

    private Connection connection;

    JDBCBookDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Book findByTitle(String title) {
        BookMapper bookMapper = new BookMapper();
        try (PreparedStatement ps =
                     connection.prepareStatement("select * from books where title =?")) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return bookMapper.extractFromResultSet(rs);
            }
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAll() {

        final String query = "select * from books left join authors on books.author_id = authors.id";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            Map<Long, Book> books = extractMappedBooks(rs);

            return new ArrayList<>(books.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long bookId) {
        try (PreparedStatement ps = connection.prepareStatement("delete from books where id = ?")) {
            ps.setLong(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Long, Book> extractMappedBooks(ResultSet rs) throws SQLException {
        Map<Long, Book> bookMap = new HashMap<>();
        Map<Long, Author> authorMap = new HashMap<>();


        BookMapper bookMapper = new BookMapper();
        AuthorMapper authorMapper = new AuthorMapper();


        while (rs.next()) {
            Book book = bookMapper.extractFromResultSet(rs);
            Author author = authorMapper.extractFromResultSet(rs);


            author = authorMapper.makeUnique(authorMap, author, rs);
            book = bookMapper.makeUnique(bookMap, book, rs);

            book.setAuthor(author);

        }
        return bookMap;
    }

    @Override
    public void create(Book entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement("insert into books(title, author_id, pages, year, img_url, amount) values (?,?,?,?,?,?)")) {
            ps.setString(1, entity.getTitle());
            ps.setLong(2, entity.getAuthor().getId());
            ps.setInt(3, entity.getPages());
            ps.setInt(4, entity.getYear());
            ps.setString(5, entity.getImgUrl());
            ps.setInt(6, entity.getAmount());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public void close() {

    }
}
