package com.liba.model.dao.imp;

import com.liba.model.dao.mapper.BookMapper;
import com.liba.model.dao.mapper.TakenBookMapper;
import com.liba.model.dao.mapper.UserMapper;
import com.liba.model.entity.Book;
import com.liba.model.entity.Role;
import com.liba.model.dao.UserDAO;
import com.liba.model.entity.TakenBook;
import com.liba.model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCUserDAO implements UserDAO {

    private Connection connection;

    private String INSERT_USER = "insert into users(username, password, phone, email) values(?,?,?,?)";


    JDBCUserDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(User entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(INSERT_USER)) {

            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getEmail());

            ps.executeUpdate();

            insertRolesToDb(entity.getRole(), entity.getUsername());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertRolesToDb(Set<Role> roles, String username) {
        try {
            PreparedStatement st = connection.prepareStatement("select id from users where username=?");
            st.setString(1,username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("id");
                PreparedStatement ps =
                        connection.prepareStatement("insert into user_role(user_id, role) values (?, ?)");
                for (Role role : roles) {
                    ps.setLong(1, id);
                    ps.setString(2, role.name());
                    ps.executeUpdate();
                }
            }
            else throw new SQLException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long userId) {

        try (PreparedStatement ps = connection.prepareStatement("select * from users left join user_role on users.id = user_role.user_id " +
                "left join taken_books on users.id = taken_books.user_id " +
                "where users.id = ?")) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            Map<Long,User> user = extractMappedUser(rs);
            return user.values().stream().findAny();
        } catch (SQLException e) {
            throw new RuntimeException("Cant find id user", e);
        }
    }

    private Map<Long, User> extractMappedUser(ResultSet rs) throws SQLException{

        Map<Long, User> userMap = new LinkedHashMap<>();
        Map<Long, TakenBook> takenBookMap = new HashMap<>();

        UserMapper userMapper = new UserMapper();
        TakenBookMapper takenBookMapper = new TakenBookMapper();

        while (rs.next()) {
            User user = userMapper.extractFromResultSet(rs);
            TakenBook takenBook = takenBookMapper.extractFromResultSet(rs);

            user = userMapper.makeUnique(userMap, user, rs);
            takenBook = takenBookMapper.makeUnique(takenBookMap, takenBook, rs);

            takenBook.setUser(user);
            user.getTakenBooks().add(takenBook);

        }
        return userMap;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByUsername(String username) {
        UserMapper userMapper = new UserMapper();
        Map<Long, User> users = new HashMap<>();
        try (PreparedStatement ps =
                     connection.prepareStatement("select * from users u left join user_role ur on u.id = ur.user_id where username =?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return userMapper.extractFromRsWithALLRoles(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
