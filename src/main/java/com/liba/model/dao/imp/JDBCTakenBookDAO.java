package com.liba.model.dao.imp;

import com.liba.model.dao.TakenBookDAO;
import com.liba.model.dao.mapper.AuthorMapper;
import com.liba.model.dao.mapper.BookMapper;
import com.liba.model.dao.mapper.TakenBookMapper;
import com.liba.model.dao.mapper.UserMapper;
import com.liba.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTakenBookDAO implements TakenBookDAO {

    private Connection connection;

    public JDBCTakenBookDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<TakenBook> findAll() {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from taken_books left join users on taken_books.user_id = users.id " +
                    "left join books on taken_books.book_id = books.id left join user_role on users.id = user_role.user_id " +
                    "left join authors on books.author_id = authors.id");

            Map<Long, TakenBook> takenBookMap = extractMappedTakenBooks(rs);
            return new ArrayList<>(takenBookMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }





    @Override
    public void create(TakenBook entity) {

    }

    @Override
    public TakenBook findById(Long id) {
        return null;
    }

    @Override
    public void close() {

    }

    private Map<Long, TakenBook> extractMappedTakenBooks(ResultSet rs) throws SQLException {
        Map<Long, TakenBook> takenBookMap = new HashMap<>();
        Map<Long, User> userMap = new HashMap<>();
        Map<Long, Book> bookMap = new HashMap<>();
        Map<Long, Author> authorMap = new HashMap<>();

        TakenBookMapper takenBookMapper = new TakenBookMapper();
        UserMapper userMapper = new UserMapper();
        BookMapper bookMapper = new BookMapper();
        AuthorMapper authorMapper = new AuthorMapper();

        while (rs.next()) {
            TakenBook takenBook = takenBookMapper.extractFromResultSet(rs);
            Book book = bookMapper.extractFromResultSet(rs);
            User user = userMapper.extractFromResultSet(rs);
            Author author = authorMapper.extractFromResultSet(rs);
            Role role = Role.valueOf(rs.getString("user_role.role"));
            System.out.println("MYROLE " + role);

            user = userMapper.makeUnique(userMap, user, rs);
            book = bookMapper.makeUnique(bookMap, book, rs);
            takenBook = takenBookMapper.makeUnique(takenBookMap, takenBook, rs);
            author = authorMapper.makeUnique(authorMap, author, rs);

            book.setAuthor(author);
            user.getRole().add(role);
            takenBook.setUser(user);
            takenBook.setBook(book);
        }
        return takenBookMap;
    }
}
