package com.liba.model.dao.imp;

import com.liba.model.dao.BookDAO;
import com.liba.model.dao.mapper.AuthorMapper;
import com.liba.model.dao.mapper.BookMapper;
import com.liba.model.dao.mapper.TakenBookMapper;
import com.liba.model.dao.mapper.UserMapper;
import com.liba.model.entity.Author;
import com.liba.model.entity.Book;
import com.liba.model.entity.TakenBook;
import com.liba.model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCBookDAO implements BookDAO {

    private Connection connection;

    JDBCBookDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> findByTitle(String searchValue) {

        try (PreparedStatement ps = connection.prepareStatement("select * from books left join authors on books.author_id = authors.id where books.title like ? ")) {
            ps.setString(1, "%" + searchValue + "%" );
            ResultSet rs = ps.executeQuery();
            Map<Long, Book> books = extractMappedBooks(rs);

            return new ArrayList<>(books.values());
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
    public Optional<Book> findById(Long bookId) {

        try (PreparedStatement ps = connection.prepareStatement("select * from books left join taken_books on books.id = taken_books.book_id where books.id = ?")) {
            ps.setLong(1, bookId);
            ResultSet rs = ps.executeQuery();


            Map<Long, Book> book = extractMappedTakenBooks(rs);
            return book.values().stream().findAny();
        } catch (SQLException e) {
            throw new RuntimeException("No book id", e);
        }
    }

    private Map<Long, Book> extractMappedTakenBooks(ResultSet rs) throws SQLException{

        Map<Long, Book> bookMap = new LinkedHashMap<>();
        Map<Long, TakenBook> takenBookMap = new HashMap<>();

        TakenBookMapper takenBookMapper = new TakenBookMapper();
        BookMapper bookMapper = new BookMapper();


        while (rs.next()) {

            TakenBook takenBook = takenBookMapper.extractFromResultSet(rs);
            Book book = bookMapper.extractFromResultSet(rs);

            book = bookMapper.makeUnique(bookMap, book, rs);
            takenBook = takenBookMapper.makeUnique(takenBookMap, takenBook, rs);

            takenBook.setBook(book);
            book.getTakenBooks().add(takenBook);

        }
        return bookMap;
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
