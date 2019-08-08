package com.liba.model.sevice;

import com.liba.model.dao.BookDAO;
import com.liba.model.dao.FactoryDAO;
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

    public void createBook(Book book) {
        try (BookDAO dao = daoFactory.createBookDao()) {
            dao.create(book);
        } catch (Exception e) {
            log.warn("I Cant!");
            e.printStackTrace();
        }

    }
}
