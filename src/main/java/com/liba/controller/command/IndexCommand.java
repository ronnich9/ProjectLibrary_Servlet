package com.liba.controller.command;

import com.liba.model.sevice.BookService;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements Command{

    private BookService bookService;

    IndexCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String searchValue = request.getParameter("searchValue");

        if (!ObjectUtils.allNotNull(searchValue)) {
            request.setAttribute("books", bookService.findByTitle(searchValue));
        }

        return "/index.jsp";
    }
}
