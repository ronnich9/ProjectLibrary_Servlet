package com.liba.controller.command;

import com.liba.model.entity.User;
import com.liba.model.sevice.UserService;
import com.liba.utils.CommandUtils;
import com.liba.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginCommand implements Command {

    private UserService userService;

    LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null) {
            return "/login.jsp";
        }

        if(SessionUtils.checkUserIsLogged(request, username)){
            request.setAttribute("errorMessage", true);
            return "/login.jsp";
        }

        User user = userService.getUserByUsername(username);


        if (user==null) {
            request.setAttribute("message", true);
            return "/login.jsp";
        }

        CommandUtils.storeLoginedUser(request.getSession(), user);


        if (user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            Set<String> userRoles = user.getRole().stream().map(Enum::name)
                    .collect(Collectors.toSet());
            session.setAttribute("userRoles", userRoles);
            return "redirect:/index";
        } else {
            request.setAttribute("message", true);
            return "/login.jsp";
        }


    }
}
