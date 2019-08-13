package com.liba.controller.command;

import com.liba.model.entity.User;
import com.liba.model.sevice.TakenBookService;
import com.liba.utils.CommandUtils;

import javax.servlet.http.HttpServletRequest;

public class TakeBookCommand implements Command {

    private TakenBookService takenBookService;

    TakeBookCommand(TakenBookService takenBookService) {
        this.takenBookService = takenBookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandUtils.getLoginedUser(request.getSession());

        Long bookId = Long.parseLong(request.getParameter("id"));


        takenBookService.addTakenBook(bookId, user.getId());
        return "redirect:/books";
    }
}
