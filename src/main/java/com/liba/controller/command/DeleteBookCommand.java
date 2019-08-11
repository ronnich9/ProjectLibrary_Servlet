package com.liba.controller.command;

import com.liba.model.sevice.BookService;

import javax.servlet.http.HttpServletRequest;

public class DeleteBookCommand implements Command {

    private BookService bookService;

    DeleteBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long bookId = Long.parseLong(request.getParameter("id"));
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }
}
