package com.liba.model.dao.mapper;

import com.liba.model.entity.Role;
import com.liba.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        Set<Role> roles = new HashSet<>();

        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        roles.add(Role.valueOf(rs.getString("role")));
        user.setRole(roles);

        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User user, ResultSet rs) throws SQLException{
        if (cache.keySet().contains(user.getId())) {
            cache.get(user.getId()).getRole().add(Role.valueOf(rs.getString("role")));
        } else {
            cache.put(user.getId(), user);
        }
        return user;
    }

    private void completeRoles(User user, ResultSet rs) throws SQLException{
        user.getRole().add(Role.valueOf(rs.getString("role")));
    }

    public User extractFromRsWithALLRoles(ResultSet rs) throws SQLException{
        if (rs.next()) {
            User user = extractFromResultSet(rs);
            while (rs.next()) {
                completeRoles(user, rs);
            }
            return user;
        }
        return null;
    }

}
