package com.lab.epam.service;

import com.lab.epam.dao.GenericDao;
import com.lab.epam.dao.Identified;
import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlDaoFactory;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceResponse;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class CategoryService {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
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

    public Category getCategoryByName(String categotrName){
        Category category = null;
        try {
            category = mySqlCategoryDao.getCategoryByName(categotrName);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get category with category name " + categotrName);
        }

        return category;
    }




}


