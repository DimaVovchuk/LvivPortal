package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.entity.FavoritePlacesByRating;
import com.lab.epam.entity.Place;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Admin on 12.06.2015.
 */
public class PlaceService {

    MySqlPlaceDao mySqlPlaceDao = new MySqlPlaceDao();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(Place object) {
        try {
            mySqlPlaceDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public void create(Connection conn, Place object) {
        try {
            mySqlPlaceDao.create(conn, object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public Place getByPK(Integer key) {
        Place place = null;
        try {
            place = mySqlPlaceDao.getByPK(key);
            ;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
        return place;
    }

    public void update(Place object) {
        try {
            mySqlPlaceDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public void delete(Place object) {
        try {
            mySqlPlaceDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
    }

    public List<Place> getAll() {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getAll();
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }
        return places;
    }

    public List<Place> getAllWithoutDeleted() {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getAllWithoutDeleted();
            ;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return places;
    }

    public List<Place> getPlaceByCategory(Integer category_id) {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByCategory(category_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return places;
    }

    public List<Place> getPlaceByCategoryRecomended(Integer category_id) {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByCategoryRecomended(category_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return places;
    }

    public List<Place> getAllPlaceVisible() {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getAllPlaceVisible();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return places;
    }

    public List<Place> getAllPlaceRecomended() {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getAllPlaceRecomended();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return places;
    }

    public List<Place> getPlaceByUserId(Integer user_id) {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByUserId(user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place by user with id " + user_id);
        }

        return places;
    }

    public List<Place> getPlaceByWayId(Integer way_id) {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByWayId(way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place by way with id " + way_id);
        }

        return places;
    }

    public List<Place> getPlaceByWayIdDayNumber(Integer way_id, Integer day_number) {
        List<Place> places = null;
        try {
            places = mySqlPlaceDao.getPlaceByWayIdDayNumber(way_id, day_number);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place by way with id " + way_id + " " + day_number + " day_number");
        }

        return places;
    }

    public void deletePlaceByUserIdPlaceId(Integer user_id, Integer place_id) {
        try {
            mySqlPlaceDao.deletePlaceByUserIdPlaceId(user_id, place_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delet way by user_id = " + user_id + " place_id " + place_id);
        }
    }

    public Place getPlaceByLongitudeLatitude(String longitude, String latitude) {
        Place place = null;
        try {
            place = mySqlPlaceDao.getPlaceByLongitudeLatitude(longitude, latitude);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place with longitude " + longitude + " and latitude " + latitude);
        }

        return place;
    }

    public Place getPlaceByLongitudeLatitude(Connection conn, String longitude, String latitude) {
        Place place = null;
        try {
            place = mySqlPlaceDao.getPlaceByLongitudeLatitude(conn, longitude, latitude);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place with longitude " + longitude + " and latitude " + latitude);
        }

        return place;
    }

    public void createPlaceWay(Integer place_id, Integer way_id, Integer day, Integer time) {
        try {
            mySqlPlaceDao.createPlaceWay(place_id, way_id, day, time);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place_way with place_id = " + place_id + " way_id " + way_id + " day " + day);
        }
    }

    public void createPlaceWay(Connection conn, Integer place_id, Integer way_id, Integer day, Integer time) {
        try {
            mySqlPlaceDao.createPlaceWay(conn, place_id, way_id, day, time);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place_way with place_id = " + place_id + " way_id " + way_id + " day " + day);
        }
    }

    public void createPlaceUser(Integer place_id, Integer user_id) {
        try {
            mySqlPlaceDao.createPlaceUser(place_id, user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create user_place with user_id = " + user_id + " place_id " + place_id);
        }
    }

    public Integer getPlaceByUserIdPlaceId(Integer place_id, Integer user_id) {
        Integer id = null;
        try {
            id = mySqlPlaceDao.getPlaceByUserIdPlaceId(place_id, user_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get user_place id with place_id " + place_id + " and user_id " + user_id);
        }

        return id;
    }

    public void deletePlaceByWayIdPlaceId(Integer way_id, Integer place_id, Integer day_number) {
        try {
            mySqlPlaceDao.deletePlaceByWayIdPlaceId(way_id, place_id, day_number);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delet place_way by way_id = " + way_id + " place_id " + place_id);
        }
    }

    public void deletePlaceByWayIdDayNumber(Integer way_id, Integer day_number) {
        try {
            mySqlPlaceDao.deletePlaceByWayIdDayNumber(way_id, day_number);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delet place_way by way_id = " + way_id + " day_number " + day_number);
        }
    }

    public Integer createAndReturnIndex(Place place) {
        return mySqlPlaceDao.createAndReturnIndex(place);
    }

    public List<Place> getAllVisbleUserCustomPlace(Integer usedID) {
        List<Place> list=null;
        try {
            list = mySqlPlaceDao.getAllVisibleUserCustomPlace(usedID);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant find user custom places");
        }
        return list;
    }
    public List<Place> getAllVisbleUserFavorPlace(Integer usedID) {
        List<Place> list=null;
        try {
            list = mySqlPlaceDao.getAllVisibleUserFavorPlace(usedID);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant find user custom places");
        }
        return list;
    }

    public List<FavoritePlacesByRating> getPlacesByRating(){
        List<FavoritePlacesByRating> placeRating = null;
        try {
            placeRating = mySqlPlaceDao.getPlacesByRating();
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return placeRating;
    }

    public void setPlaceIsRecommended(Integer place_id) {
        try {
            mySqlPlaceDao.setPlaceIsRecommended(place_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant set is_recommended by place_id " + place_id);
        }
    }

    public List<Place> getAllConfirmCustomPlace() {
        List<Place> list=null;
        try {
            list = mySqlPlaceDao.getAllConfirmCustomPlace();
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant find user custom places");
        }
        return list;
    }

    public List<Place> getAllConfirmRecommendedPlace() {
        List<Place> list=null;
        try {
            list = mySqlPlaceDao.getAllConfirmRecommendedPlace();
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant find user Recommended places");
        }
        return list;
    }
}
