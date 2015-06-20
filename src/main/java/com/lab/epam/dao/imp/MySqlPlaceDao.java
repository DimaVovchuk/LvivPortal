package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import com.lab.epam.transformer.Transformer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlPlaceDao extends AbstractJDBCDao<Place, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private static final String GET_PLACE_BY_CATEGORY = "SELECT * FROM place WHERE category_id = ?";
    private static final String GET_PLACE_BY_USER_ID = "SELECT p.id, p.adress, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.place_time, p.deleted FROM place AS p JOIN user_place AS up JOIN user AS u WHERE up.user_id = u.id AND up.deleted = false AND up.place_id = p.id AND u.id = ?";
    private static final String GET_PLACE_BY_WAY_ID = "SELECT p.id, p.adress, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.place_time, p.deleted FROM place AS p JOIN place_way AS pw JOIN way AS w WHERE pw.way_id = w.id AND pw.place_id = p.id AND w.id = ?";
    private static final String DELETE_PLACE_BY_USER_ID_PLACE_ID = "UPDATE user_place SET deleted = true WHERE user_id = ? AND place_id = ?";
    private static final String GET_PLACE_BY_LATITUDE_LONGITUDE = "SELECT * FROM place WHERE longitude = ? AND latitude = ?";
    private static final String CREATE_PLACE_WAY = "INSERT INTO place_way (place_id, way_id, day_number) VALUES (?,?,?);";

    private class PersistGroup extends Place {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceDao() {
    }

    public Class getClassModel() {
        return Place.class;
    }


    public List<Place> getPlaceByCategory(Integer category_id) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_CATEGORY)) {
            statement.setInt(1, category_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;



    }

    public List<Place> getPlaceByUserId(Integer user_id) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_USER_ID)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            loger.info("Get places from user with id" + user_id + " is succesfull " + rs);
            list = parseResultSet(rs);
            loger.info("Parse result with Transformer is succesfull list = " + list);
            if (list.size() <= 0){
                loger.info("DB has any place from user with " + user_id + " user_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places from user with " + user_id + " user_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }


    public List<Place> getPlaceByWayId(Integer way_id) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_WAY_ID)) {
            statement.setInt(1, way_id);
            ResultSet rs = statement.executeQuery();
            loger.info("Get places from way with id" + way_id + " is succesfull " + rs);
            list = parseResultSet(rs);
            loger.info("Parse result with Transformer is succesfull list = " + list);
            if (list.size() <= 0){
                loger.info("DB has any place from way with " + way_id + " way_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places from way with " + way_id + " way_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public void deletePlaceByUserIdPlaceId(Integer user_id, Integer place_id) throws PersistException {

        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(DELETE_PLACE_BY_USER_ID_PLACE_ID)) {
            try {
                statement.setObject(1, user_id);
                statement.setObject(2, place_id);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            loger.warn("Cant delete way from user with " + user_id + " user_id and " + place_id + " place_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }

    }

    public Place getPlaceByLongitudeLatitude(String longitude, String latitude) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_LATITUDE_LONGITUDE)) {
            statement.setString(1, longitude);
            statement.setString(2, latitude);
            ResultSet rs = statement.executeQuery();
            loger.info("Get places with longitude " + longitude + " and latitude " + latitude + " is succesfull ");
            list = parseResultSet(rs);
            loger.info("Parse result with Transformer is succesfull");
            if (list.size() <= 0){
                loger.info("DB has any place with longitude " + longitude + " and latitude " + latitude);
                return null;
            }
            if (list.size() > 1){
                loger.info("DB has more than one place with longitude " + longitude + " and latitude " + latitude);
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places with longitude " + longitude + " and latitude " + latitude);
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list.iterator().next();
    }

    public void createPlaceWay(Integer place_id, Integer way_id, Integer day) throws PersistException {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(CREATE_PLACE_WAY)) {
            statement.setInt(1, place_id);
            statement.setInt(2, way_id);
            statement.setInt(3, day);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                System.out.println("Create is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }


    }


}

