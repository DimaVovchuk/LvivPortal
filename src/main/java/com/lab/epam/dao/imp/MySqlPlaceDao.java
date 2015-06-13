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
    private static final String GET_PLACE_BY_USER_ID = "SELECT p.id, p.adress, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.deleted FROM place AS p JOIN user_place AS up JOIN user AS u WHERE up.user_id = u.id AND up.place_id = p.id AND u.id = ?";

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

}

