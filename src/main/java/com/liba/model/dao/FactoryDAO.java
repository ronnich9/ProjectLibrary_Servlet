package com.liba.model.dao;

import com.liba.model.dao.imp.JDBCFactoryDAO;

public abstract class FactoryDAO {

    private static FactoryDAO daoFactory;

    public abstract UserDAO createUserDao();
    public abstract AuthorDAO createAuthorDao();
    public abstract BookDAO createBookDao();
    public abstract TakenBookDAO createTakenBookDao();

    public static FactoryDAO getInstance(){
        if( daoFactory == null ){
            synchronized (FactoryDAO.class){
                if(daoFactory==null){
                    FactoryDAO temp = new JDBCFactoryDAO();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
