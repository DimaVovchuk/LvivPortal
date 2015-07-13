package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceRating;
import com.lab.epam.entity.PlaceResponse;
import com.lab.epam.entity.Role;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Admin on 15.06.2015.
 */
public class MySqlPlaceRatingDao extends AbstractJDBCDao<PlaceRating, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String GET_PLACE_RATING_BY_PLACE_ID_USER_ID = "SELECT * FROM place_rating WHERE place_id = ? AND user_id = ?";

    private class PersistGroup extends PlaceRating {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceRatingDao() {
    }

    public Class getClassModel() {
        return PlaceRating.class;
    }

    public PlaceRating getPlaceRatingByPlaceAndUser(Integer place_id, Integer user_id) throws PersistException {
        List<PlaceRating> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_RATING_BY_PLACE_ID_USER_ID)) {
            statement.setInt(1, place_id);
            statement.setInt(2, user_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list.size() <= 0){
               // loger.info("DB has any place_rating with " + place_id + " place_id and user_id " + user_id);
                return null;
            }
            if (list.size() != 1){
                loger.info("DB has more than one place_rating with " + place_id + " place_id and user_id " + user_id);
            }
        } catch (Exception e) {
            loger.warn("Cant get place_rating by place id " + place_id + " and user_id " + user_id);
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        loger.info("getPlaceRatingByPlaceAndUser method");

        return list.iterator().next();
    }

}
