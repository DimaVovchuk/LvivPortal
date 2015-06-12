package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.entity.User;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class UserService {

    MySqlUserDao mySqlUserDao = new MySqlUserDao();

    public void create(User object) throws PersistException {
        mySqlUserDao.create(object);
    }

    public User getByPK(Integer key) throws PersistException{
        return mySqlUserDao.getByPK(key);
    }

    public void update(User object) throws PersistException{
        mySqlUserDao.update(object);
    }

    public void delete(User object) throws PersistException{
        mySqlUserDao.delete(object);
    }

    public List<User> getAll() throws PersistException{
        return mySqlUserDao.getAll();
    }

    public List<User> getAllWithoutDeleted() throws PersistException{
        return mySqlUserDao.getAllWithoutDeleted();
    }
    public User geUserByLogin(String login){
        return mySqlUserDao.getUserByLogin(login);
    }

}
