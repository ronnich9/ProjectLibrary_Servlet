package com.liba.controller.command;

import com.liba.model.sevice.AuthorService;

import javax.servlet.http.HttpServletRequest;

public class DeleteAuthorCommand implements Command {

    private AuthorService authorService;

    DeleteAuthorCommand(AuthorService authorService) {
        this.authorService = authorService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Long authorId = Long.parseLong(request.getParameter("id"));
        authorService.deleteAuthor(authorId);
        return "redirect:/authors";
    }
}
