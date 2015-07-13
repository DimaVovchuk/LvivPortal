package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.PlaceRating;
import com.lab.epam.entity.WayRating;
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
 * Created by Admin on 01.07.2015.
 */
public class MySqlWayRatingDao  extends AbstractJDBCDao<WayRating, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String GET_WAY_RATING_BY_WAY_ID_USER_ID = "SELECT * FROM way_rating WHERE way_id = ? AND user_id = ?";

    private class PersistGroup extends WayRating {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlWayRatingDao() {
    }

    public Class getClassModel() {
        return WayRating.class;
    }

    public WayRating getWayRatingByWayAndUser(Integer way_id, Integer user_id) throws PersistException {
        List<WayRating> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_WAY_RATING_BY_WAY_ID_USER_ID)) {
            statement.setInt(1, way_id);
            statement.setInt(2, user_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list.size() <= 0){
                // loger.info("DB has any place_rating with " + place_id + " place_id and user_id " + user_id);
                return null;
            }
            if (list.size() != 1){
                loger.info("DB has more than one way_rating with " + way_id + " way_id and user_id " + user_id);
            }
        } catch (Exception e) {
            loger.warn("Cant get way_rating by way id " + way_id + " and user_id " + user_id);
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        loger.info("getWayRatingByWayAndUser method");
        return list.iterator().next();
    }



}
