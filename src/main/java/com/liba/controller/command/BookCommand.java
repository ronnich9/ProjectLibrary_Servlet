package com.liba.controller.command;

import com.liba.model.sevice.BookService;

import javax.servlet.http.HttpServletRequest;

public class BookCommand implements Command{
    private BookService bookService;


    BookCommand(BookService bookService) {
        this.bookService = bookService;

    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("books", bookService.getAllBooks());

        return "/books.jsp";
    }
}
