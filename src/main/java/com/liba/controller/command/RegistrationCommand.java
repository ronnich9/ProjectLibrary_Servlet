package com.liba.controller.command;

import com.liba.model.dao.FactoryDAO;
import com.liba.model.dao.UserDAO;
import com.liba.model.entity.Role;
import com.liba.model.entity.User;
import com.liba.model.sevice.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;

public class RegistrationCommand implements Command {
    private static final Logger log = LogManager.getLogger();
    private UserService userService;

    FactoryDAO daoFactory = FactoryDAO.getInstance();

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");


        if (!(Objects.nonNull(username) &&
                Objects.nonNull(password) && Objects.nonNull(phone) && Objects.nonNull(email)) ) {
            return "/registration.jsp";
        }

        User user = new User(username, password, phone, email, Collections.singleton(Role.USER));

//        UserDAO dao = daoFactory.createUserDao();
//
//        dao.create(user);
        log.info("User to be registered: " + user);


        if (userService.registerUser(user)) {
            log.info("User successfully registered");
            return "redirect:/login";
        } else {
            log.info("User can not be registered");
            request.setAttribute("error", true);
            return "/index.jsp";
        }
    }
}
