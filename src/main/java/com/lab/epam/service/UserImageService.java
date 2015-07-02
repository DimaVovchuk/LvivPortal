package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserImageDao;
import com.lab.epam.dao.imp.MySqlUserImageDao;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 13.06.2015.
 */
public class UserImageService {

    MySqlUserImageDao mySqlUserImageDao = new MySqlUserImageDao();

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(UserImage object){
        try {
            mySqlUserImageDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create user images");
        }
    }

    public UserImage getByPK(Integer key){
        UserImage userImage = null;
        try {
            userImage =  mySqlUserImageDao.getByPK(key);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get user images by id");
        }
        return userImage;
    }

    public void update(UserImage object){
        try {
            mySqlUserImageDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update user images");
        }
    }

    public void delete(UserImage object){
        try {
            mySqlUserImageDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delete user images");
        }
    }

    public List<UserImage> getAll(){
        List<UserImage> userImage = null;
        try {
            userImage = mySqlUserImageDao.getAll();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all user images");
        }

        return userImage;
    }

    public List<UserImage> getAllWithoutDeleted(){
        List<UserImage> userImage = null;
        try {
            userImage = mySqlUserImageDao.getAllWithoutDeleted();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all user images");
        }

        return userImage;
    }

    public List<UserImage> getUserImageByUserId(Integer user_id){
        List<UserImage> userImage = null;
        try {
            userImage =  mySqlUserImageDao.getUserImageByUserId(user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get user images from DB");
        }
        return userImage;
    }

    public UserImage getUserImageByUserIdOne(Integer user_id){
        UserImage userImage = null;
        try {
            userImage =  mySqlUserImageDao.getUserImageByUserIdOne(user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get user image from DB");
        }
        return userImage;
    }



}
