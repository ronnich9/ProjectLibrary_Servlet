package com.liba.model.sevice;

import com.liba.model.dao.BookDAO;
import com.liba.model.dao.FactoryDAO;
import com.liba.model.dao.TakenBookDAO;
import com.liba.model.entity.Book;
import com.liba.model.entity.TakenBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
