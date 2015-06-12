package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDescriptionDao;
import com.lab.epam.dao.imp.MySqlPlaceImageDao;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 12.06.2015.
 */
public class PlaceImageService {

    MySqlPlaceImageDao mySqlPlaceImageDao = new MySqlPlaceImageDao();

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(PlaceImage object){
        try {
            mySqlPlaceImageDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public PlaceImage getByPK(Integer key){
        PlaceImage placeImage = null;
        try {
            placeImage =  mySqlPlaceImageDao.getByPK(key);;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
        return placeImage;
    }

    public void update(PlaceImage object){
        try {
            mySqlPlaceImageDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public void delete(PlaceImage object){
        try {
            mySqlPlaceImageDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public List<PlaceImage> getAll(){
        List<PlaceImage> placeImage = null;
        try {
            placeImage = mySqlPlaceImageDao.getAll();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return placeImage;
    }

    public List<PlaceImage> getAllWithoutDeleted(){
        List<PlaceImage> placesDescription = null;
        try {
            placesDescription = mySqlPlaceImageDao.getAllWithoutDeleted();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return placesDescription;
    }

    public PlaceImage getPlaceImageByPlaceId(Integer place_id){
        PlaceImage placeImage = null;
        try {
            placeImage =  mySqlPlaceImageDao.getPlaceImageByPlaceId(place_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get images from DB");
        }
        return placeImage;
    }


}
