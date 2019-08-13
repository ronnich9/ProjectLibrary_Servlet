package com.liba.model.sevice;

import com.liba.model.dao.BookDAO;
import com.liba.model.dao.FactoryDAO;
import com.liba.model.dao.TakenBookDAO;
import com.liba.model.dao.UserDAO;
import com.liba.model.entity.Book;
import com.liba.model.entity.TakenBook;
import com.liba.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class TakenBookService {
    private static final Logger log = LogManager.getLogger();
    private FactoryDAO daoFactory = FactoryDAO.getInstance();

    public List<TakenBook> getAllTakenBooks() {
        try (TakenBookDAO takenBookDAO = daoFactory.createTakenBookDao()) {
            return takenBookDAO.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void addTakenBook(Long bookId, Long userId) {
        try (TakenBookDAO takenBookDAO = daoFactory.createTakenBookDao();
             BookDAO bookDAO = daoFactory.createBookDao();
             UserDAO userDAO = daoFactory.createUserDao()) {

            Book book = bookDAO.findById(bookId).orElseThrow(() ->
                    new IllegalArgumentException("Invalid book id: " + bookId));
            User user = userDAO.findById(userId).orElseThrow(() ->
                    new IllegalArgumentException("Invalid user id: " + userId));


            TakenBook takenBook = new TakenBook(user, book, LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
            user.getTakenBooks().add(takenBook);
            takenBookDAO.create(takenBook);
        }


    }
}
