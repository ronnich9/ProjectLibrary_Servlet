package com.liba.controller.command;

import com.liba.model.sevice.BookService;
import com.liba.model.sevice.TakenBookService;
import com.liba.model.sevice.UserService;

import javax.servlet.http.HttpServletRequest;

public class TakenBooksCommand implements Command {

    private TakenBookService takenBookService;

    TakenBooksCommand(TakenBookService takenBookService) {
        this.takenBookService = takenBookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("takenBooks", takenBookService.getAllTakenBooks());

        return "/all_taken_books.jsp";
    }
}
