package com.liba.model.sevice;

import com.liba.model.dao.FactoryDAO;
import com.liba.model.dao.UserDAO;
import com.liba.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);
    FactoryDAO daoFactory = FactoryDAO.getInstance();

    public User getUserByUsername(String username) {
        try (UserDAO dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username);
        } catch (Exception e) {
            log.warn("Cant get user!");

            return null;
        }
    }

    public boolean registerUser(User user){

        try (UserDAO dao = daoFactory.createUserDao()) {
            User userFromDb = dao.findByUsername(user.getUsername());
            if (userFromDb != null) {
                log.warn("login not unique!");
                return false;
            }
            System.out.println(user.getUsername());
            dao.create(user);

            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            e.getStackTrace();
            return false;
        }
    }
}
