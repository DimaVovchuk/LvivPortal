package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlPlaceResponseDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceResponse;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class PlaceResponseService {

    MySqlPlaceResponseDao mySqlPlaceResponseDao = new MySqlPlaceResponseDao();

    public void create(PlaceResponse object) throws PersistException {
        mySqlPlaceResponseDao.create(object);
    }

    public PlaceResponse getByPK(Integer key) throws PersistException{
        return mySqlPlaceResponseDao.getByPK(key);
    }

    public void update(PlaceResponse object) throws PersistException{
        mySqlPlaceResponseDao.update(object);
    }

    public void delete(PlaceResponse object) throws PersistException{
        mySqlPlaceResponseDao.delete(object);
    }

    public List<PlaceResponse> getAll() throws PersistException{
        return mySqlPlaceResponseDao.getAll();
    }

    public List<PlaceResponse> getAllWithoutDeleted() throws PersistException{
        return mySqlPlaceResponseDao.getAllWithoutDeleted();
    }
}
