package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlWayDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Way;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class ServiceWay {

    MySqlWayDao mySqlWayDao = new MySqlWayDao();

    public void create(Way object) throws PersistException {
        mySqlWayDao.create(object);
    }

    public Way getByPK(Integer key) throws PersistException{
        return mySqlWayDao.getByPK(key);
    }

    public void update(Way object) throws PersistException{
        mySqlWayDao.update(object);
    }

    public void delete(Way object) throws PersistException{
        mySqlWayDao.delete(object);
    }

    public List<Way> getAll() throws PersistException{
        return mySqlWayDao.getAll();
    }

    public List<Way> getAllWithoutDeleted() throws PersistException{
        return mySqlWayDao.getAllWithoutDeleted();
    }

}

