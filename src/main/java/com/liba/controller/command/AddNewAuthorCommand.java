package com.liba.controller.command;

import com.liba.model.entity.Author;
import com.liba.model.sevice.AuthorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AddNewAuthorCommand implements Command {

    private AuthorService authorService;

    public AddNewAuthorCommand(AuthorService authorService) {
        this.authorService = authorService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");

        if (!(Objects.nonNull(name)) ) {
            return "/create_author.jsp";
        }

        Author author = new Author(name);

        if (authorService.createAuthor(author)) {
            return "redirect:/authors";
        } else {
            request.setAttribute("error", true);
            return "redirect:/authors";
        }
    }
}
