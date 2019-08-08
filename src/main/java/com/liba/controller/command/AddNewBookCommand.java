package com.liba.controller.command;

import com.liba.model.entity.Author;
import com.liba.model.entity.Book;
import com.liba.model.sevice.AuthorService;
import com.liba.model.sevice.BookService;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class AddNewBookCommand implements Command {

    private AuthorService authorService;
    private BookService bookService;

    AddNewBookCommand(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        Author author = new Author();
        //author.setName(request.getParameter("author"));
        author.setId(Long.valueOf(request.getParameter("author")));
        String pages = request.getParameter("pages");
        String year = request.getParameter("year");
        String img = request.getParameter("imgUrl");
        String amount = request.getParameter("amount");


        if (!ObjectUtils.allNotNull(title, pages, year, img, amount)) {
            request.setAttribute("authors", authorService.getAllAuthors());
            return "/create_book.jsp";
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPages(Integer.valueOf(pages));
        book.setYear(Integer.valueOf(year));
        book.setImgUrl(img);
        book.setAmount(Integer.valueOf(amount));


        bookService.createBook(book);
        return "redirect:/activities";
    }
}
