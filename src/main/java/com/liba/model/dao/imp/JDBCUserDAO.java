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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
    public User findById(Long id) {
        return null;
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
