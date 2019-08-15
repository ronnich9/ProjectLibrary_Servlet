package com.liba.controller.command;

import com.liba.model.entity.User;
import com.liba.model.sevice.TakenBookService;
import com.liba.utils.CommandUtils;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements Command {

    private TakenBookService takenBookService;

    ProfileCommand(TakenBookService takenBookService) {
        this.takenBookService = takenBookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandUtils.getLoginedUser(request.getSession());

        Long userId = user.getId();
        System.out.println("User ID " + userId);
        request.setAttribute("takenBooks", takenBookService.getBooksByUserId(userId));
        return "/profiles.jsp";
    }
}
