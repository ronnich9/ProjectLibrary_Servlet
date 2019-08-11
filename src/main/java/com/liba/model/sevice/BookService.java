package com.liba.model.sevice;

import com.liba.model.dao.AuthorDAO;
import com.liba.model.dao.BookDAO;
import com.liba.model.dao.FactoryDAO;
import com.liba.model.entity.Author;
import com.liba.model.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class BookService {
    private static final Logger log = LogManager.getLogger();
    private FactoryDAO daoFactory = FactoryDAO.getInstance();

    public List<Book> getAllBooks() {
        try (BookDAO dao = daoFactory.createBookDao()) {
            return dao.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void createBook(Book book, Long authorId) {
        try (BookDAO bookDAO = daoFactory.createBookDao();
             AuthorDAO authorDAO = daoFactory.createAuthorDao()) {
//            Author author = authorDAO.findById(authorId).orElseThrow(() ->
//                    new IllegalArgumentException("Invalid author id: " + authorId));
            Author author = authorDAO.findById(authorId);
            book.setAuthor(author);

            bookDAO.create(book);
        } catch (Exception e) {
            log.warn("Cannot create a book!");
            e.printStackTrace();
        }

    }

    public void deleteBook(Long bookId) {
        try (BookDAO bookDAO = daoFactory.createBookDao()) {
            bookDAO.delete(bookId);
        } catch (Exception e) {
            log.warn("Can not delete book", e);
        }
    }
}
