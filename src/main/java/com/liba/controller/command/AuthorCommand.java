package com.liba.controller.command;

import com.liba.model.sevice.AuthorService;

import javax.servlet.http.HttpServletRequest;

public class AuthorCommand implements Command {

    private AuthorService authorService;

    AuthorCommand(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("authors", authorService.getAllAuthors());
        return "/authors.jsp";
    }
}
