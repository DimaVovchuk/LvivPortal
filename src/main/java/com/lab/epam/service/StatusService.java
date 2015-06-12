package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlStatusDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Status;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class StatusService {

    MySqlStatusDao mySqlStatusDao = new MySqlStatusDao();

    public void create(Status object) throws PersistException {
        mySqlStatusDao.create(object);
    }

    public Status getByPK(Integer key) throws PersistException{
        return mySqlStatusDao.getByPK(key);
    }

    public void update(Status object) throws PersistException{
        mySqlStatusDao.update(object);
    }

    public void delete(Status object) throws PersistException{
        mySqlStatusDao.delete(object);
    }

    public List<Status> getAll() throws PersistException{
        return mySqlStatusDao.getAll();
    }

    public List<Status> getAllWithoutDeleted() throws PersistException{
        return mySqlStatusDao.getAllWithoutDeleted();
    }

}
