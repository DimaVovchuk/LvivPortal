package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserRatingDao;
import com.lab.epam.entity.UserRating;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 10.07.2015.
 */
public class UserRatingService {

    MySqlUserRatingDao mySqlUserRatingDao = new MySqlUserRatingDao();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(UserRating object){
        try {
            mySqlUserRatingDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place rating");
        }
    }

    public UserRating getByPK(Integer key){
        UserRating userRating = null;
        try {
            userRating =  mySqlUserRatingDao.getByPK(key);;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place rating by id");
        }
        return userRating;
    }

    public void update(UserRating object){
        try {
            mySqlUserRatingDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update place rating");
        }
    }

    public void delete(UserRating object){
        try {
            mySqlUserRatingDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delete place rating");
        }
    }

    public List<UserRating> getAll(){
        List<UserRating> userRating = null;
        try {
            userRating = mySqlUserRatingDao.getAll();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place rating");
        }

        return userRating;
    }

    public List<UserRating> getAllWithoutDeleted(){
        List<UserRating> userRating = null;
        try {
            userRating = mySqlUserRatingDao.getAllWithoutDeleted();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place rating");
        }

        return userRating;
    }

    public UserRating getUseRatingByCompanyAndUser(Integer company_id, Integer user_id){
        UserRating userRating = null;
        try {
            userRating = mySqlUserRatingDao.getUseRatingByCompanyAndUser(company_id, user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place rating by company_id " + company_id + " and user_id " + user_id);
        }

        return userRating;
    }

}
