package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.entity.Place;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 12.06.2015.
 */
public class PlaceService {

    MySqlPlaceDao mySqlPlaceDao = new MySqlPlaceDao();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(Place object){
        try {
            mySqlPlaceDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public Place getByPK(Integer key){
        Place place = null;
        try {
            place =  mySqlPlaceDao.getByPK(key);;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
        return place;
    }

    public void update(Place object){
        try {
            mySqlPlaceDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public void delete(Place object){
        try {
            mySqlPlaceDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public List<Place> getAll(){
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getAll();
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
        return places;
    }

    public List<Place> getAllWithoutDeleted(){
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getAllWithoutDeleted();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return places;
    }

    public List<Place> getPlaceByCategory(Integer category_id){
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByCategory(category_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return places;
    }

    public List<Place> getPlaceByUserId(Integer user_id){
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByUserId(user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place by user with id " + user_id);
        }

        return places;
    }

    public List<Place> getPlaceByWayId(Integer way_id){
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByWayId(way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place by way with id " + way_id);
        }

        return places;
    }

    public void deletePlaceByUserIdPlaceId(Integer user_id, Integer place_id){
        try {
            mySqlPlaceDao.deletePlaceByUserIdPlaceId(user_id, place_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delet way by user_id = " + user_id + " place_id " + place_id);
        }
    }

    public Place getPlaceByLongitudeLatitude(String longitude, String latitude){
        Place place = null;
        try {
            place = mySqlPlaceDao.getPlaceByLongitudeLatitude(longitude, latitude);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place with longitude " + longitude + " and latitude " + latitude);
        }

        return place;
    }

    public void createPlaceWay(Integer place_id, Integer way_id, Integer day){
        try {
            mySqlPlaceDao.createPlaceWay(place_id, way_id,day);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place_way with place_id = " + place_id + " way_id " + way_id + " day " + day);
        }
    }

}
