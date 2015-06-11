package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Place;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class ServicePlace {
    MySqlPlaceDao mySqlPlaceDao = new MySqlPlaceDao();

    public void create(Place object) throws PersistException {
        mySqlPlaceDao.create(object);
    }

    public Place getByPK(Integer key) throws PersistException{
        return mySqlPlaceDao.getByPK(key);
    }

    public void update(Place object) throws PersistException{
        mySqlPlaceDao.update(object);
    }

    public void delete(Place object) throws PersistException{
        mySqlPlaceDao.delete(object);
    }

    public List<Place> getAll() throws PersistException{
        return mySqlPlaceDao.getAll();
    }

    public List<Place> getAllWithoutDeleted() throws PersistException{
        return mySqlPlaceDao.getAllWithoutDeleted();
    }
}
