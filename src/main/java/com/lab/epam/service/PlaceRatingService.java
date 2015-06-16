package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDescriptionDao;
import com.lab.epam.dao.imp.MySqlPlaceRatingDao;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceRating;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 16.06.2015.
 */
public class PlaceRatingService {

    MySqlPlaceRatingDao mySqlPlaceRatingDao = new MySqlPlaceRatingDao();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(PlaceRating object){
        try {
            mySqlPlaceRatingDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place rating");
        }
    }

    public PlaceRating getByPK(Integer key){
        PlaceRating placeRating = null;
        try {
            placeRating =  mySqlPlaceRatingDao.getByPK(key);;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place rating by id");
        }
        return placeRating;
    }

    public void update(PlaceRating object){
        try {
            mySqlPlaceRatingDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update place rating");
        }
    }

    public void delete(PlaceRating object){
        try {
            mySqlPlaceRatingDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delete place rating");
        }
    }

    public List<PlaceRating> getAll(){
        List<PlaceRating> placeRating = null;
        try {
            placeRating = mySqlPlaceRatingDao.getAll();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place rating");
        }

        return placeRating;
    }

    public List<PlaceRating> getAllWithoutDeleted(){
        List<PlaceRating> placeRating = null;
        try {
            placeRating = mySqlPlaceRatingDao.getAllWithoutDeleted();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place rating");
        }

        return placeRating;
    }

    public PlaceRating getPlaceRatingByPlaceAndUser(Integer place_id, Integer user_id){
        PlaceRating placeRating = null;
        try {
            placeRating = mySqlPlaceRatingDao.getPlaceRatingByPlaceAndUser(place_id, user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place rating by place_id " + place_id + " and user_id " + user_id);
        }

        return placeRating;
    }

}
