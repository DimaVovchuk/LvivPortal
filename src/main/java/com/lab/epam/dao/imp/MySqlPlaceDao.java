package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import com.lab.epam.transformer.Transformer;

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
    private static final String GET_PLACE_BY_CATEGORY = "SELECT * FROM place WHERE category_id = ?";

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

}

