package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlWayRatingDao;
import com.lab.epam.entity.WayRating;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 01.07.2015.
 */
public class WayRatingService {

    MySqlWayRatingDao mySqlWayRatingDao = new MySqlWayRatingDao();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(WayRating object){
        try {
            mySqlWayRatingDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place rating");
        }
    }

    public WayRating getByPK(Integer key){
        WayRating wayRating = null;
        try {
            wayRating =  mySqlWayRatingDao.getByPK(key);;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place rating by id");
        }
        return wayRating;
    }

    public void update(WayRating object){
        try {
            mySqlWayRatingDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update place rating");
        }
    }

    public void delete(WayRating object){
        try {
            mySqlWayRatingDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delete place rating");
        }
    }

    public List<WayRating> getAll(){
        List<WayRating> wayRating = null;
        try {
            wayRating = mySqlWayRatingDao.getAll();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place rating");
        }

        return wayRating;
    }

    public List<WayRating> getAllWithoutDeleted(){
        List<WayRating> wayRating = null;
        try {
            wayRating = mySqlWayRatingDao.getAllWithoutDeleted();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place rating");
        }

        return wayRating;
    }

    public WayRating getWayRatingByWayAndUser(Integer way_id, Integer user_id){
        WayRating wayRating = null;
        try {
            wayRating = mySqlWayRatingDao.getWayRatingByWayAndUser(way_id, user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place rating by way_id " + way_id + " and user_id " + user_id);
        }

        return wayRating;
    }

}
