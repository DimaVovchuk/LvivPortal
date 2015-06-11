package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlWayResponseDao;
import com.lab.epam.entity.WayResponse;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class ServiceWayResponse {

    MySqlWayResponseDao mySqlWayResponseDao = new MySqlWayResponseDao();

    public void create(WayResponse object) throws PersistException {
        mySqlWayResponseDao.create(object);
    }

    public WayResponse getByPK(Integer key) throws PersistException{
        return mySqlWayResponseDao.getByPK(key);
    }

    public void update(WayResponse object) throws PersistException{
        mySqlWayResponseDao.update(object);
    }

    public void delete(WayResponse object) throws PersistException{
        mySqlWayResponseDao.delete(object);
    }

    public List<WayResponse> getAll() throws PersistException{
        return mySqlWayResponseDao.getAll();
    }

    public List<WayResponse> getAllWithoutDeleted() throws PersistException{
        return mySqlWayResponseDao.getAllWithoutDeleted();
    }

}
