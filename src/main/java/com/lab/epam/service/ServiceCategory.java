package com.lab.epam.service;

import com.lab.epam.dao.GenericDao;
import com.lab.epam.dao.Identified;
import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlDaoFactory;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceResponse;
import com.lab.epam.persistant.ConnectionPool;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class ServiceCategory {

    MySqlCategoryDao mySqlCategoryDao = new MySqlCategoryDao();

    public void create(Category object) throws PersistException{
        mySqlCategoryDao.create(object);
    }

    public Category getByPK(Integer key) throws PersistException{
        return mySqlCategoryDao.getByPK(key);
    }

    public void update(Category object) throws PersistException{
        mySqlCategoryDao.update(object);
    }

    public void delete(Category object) throws PersistException{
        mySqlCategoryDao.delete(object);
    }

    public List<Category> getAll() throws PersistException{
        return mySqlCategoryDao.getAll();
    }

    public List<Category> getAllWithoutDeleted() throws PersistException{
        return mySqlCategoryDao.getAllWithoutDeleted();
    }
}


