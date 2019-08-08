package com.liba.model.sevice;

import com.liba.model.dao.AuthorDAO;
import com.liba.model.dao.FactoryDAO;
import com.liba.model.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class AuthorService {
    private static final Logger log = LogManager.getLogger();
    private FactoryDAO daoFactory = FactoryDAO.getInstance();

    public List<Author> getAllAuthors() {
        try (AuthorDAO dao = daoFactory.createAuthorDao()) {
            return dao.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean createAuthor(Author author) {

        try (AuthorDAO dao = daoFactory.createAuthorDao()) {
            Author userFromDb = dao.findByName(author.getName());
            if (userFromDb != null) {
                log.warn("author is already exist!");
                return false;
            }
            dao.create(author);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            e.getStackTrace();
            return false;
        }
    }
}
