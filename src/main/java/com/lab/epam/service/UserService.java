package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class UserService {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    MySqlUserDao mySqlUserDao = new MySqlUserDao();

    public void create(User object) throws PersistException {
        mySqlUserDao.create(object);
    }

    public User getByPK(Integer key){
        User user = null;
        try {
            user = mySqlUserDao.getByPK(key);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get user by id " + key);
        }

        return user;
    }

    public void update(User object) throws PersistException {
        mySqlUserDao.update(object);
    }

    public void delete(User object) throws PersistException{
        mySqlUserDao.delete(object);
    }

    public List<User> getAll() throws PersistException {
        return mySqlUserDao.getAll();
    }

    public List<User> getAllWithoutDeleted() throws PersistException {
        return mySqlUserDao.getAllWithoutDeleted();
    }
    public User getUserByLogin(String login){
        return mySqlUserDao.getUserByLogin(login);
    }

    public User geUserByEmail(String email) {
        return mySqlUserDao.getUserByEmail(email);
    }

    public boolean checkEmail(String email) {
        return mySqlUserDao.checkEmail(email);
    }

    public boolean checkPhone(String phone) {
        return mySqlUserDao.checkPhone(phone);
    }

    public boolean checkLogin(String login) {
        return mySqlUserDao.checkLogin(login);
    }

    public Integer getRoleID(String login){
        return mySqlUserDao.getRoleID(login);
    }
}
