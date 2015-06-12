package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.dao.imp.MySqlPlaceDescriptionDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class PlaceDescriptionService {

    MySqlPlaceDescriptionDao mySqlPlaceDescriptionDao = new MySqlPlaceDescriptionDao();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(PlaceDescription object){
        try {
            mySqlPlaceDescriptionDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public PlaceDescription getByPK(Integer key){
        PlaceDescription placesDescription = null;
        try {
            placesDescription =  mySqlPlaceDescriptionDao.getByPK(key);;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
        return placesDescription;
    }

    public void update(PlaceDescription object){
        try {
            mySqlPlaceDescriptionDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public void delete(PlaceDescription object){
        try {
            mySqlPlaceDescriptionDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public List<PlaceDescription> getAll(){
        List<PlaceDescription> placesDescription = null;
        try {
            placesDescription = mySqlPlaceDescriptionDao.getAll();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return placesDescription;
    }

    public List<PlaceDescription> getAllWithoutDeleted(){
        List<PlaceDescription> placesDescription = null;
        try {
            placesDescription = mySqlPlaceDescriptionDao.getAllWithoutDeleted();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return placesDescription;
    }

    public PlaceDescription getPlaceDescriptionByIdPlace(Integer place_id, String locale){
        PlaceDescription placeDescription = null;
        try {
            placeDescription = mySqlPlaceDescriptionDao.getPlaceDescriptionByIdPlace(place_id, locale);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return placeDescription;
    }

}
