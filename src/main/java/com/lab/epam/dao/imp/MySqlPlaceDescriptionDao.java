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
import java.util.List;

/**
 * Created by Admin on 12.06.2015.
 */
public class MySqlPlaceDescriptionDao extends AbstractJDBCDao<PlaceDescription, Integer> {

    private static final String GET_LOCALE_DESCRIPTIONS_BY_PLACE = "SELECT * FROM place_description WHERE place_id = ? AND locale = ?";

    ConnectionPool connection = ConnectionManager.getConnection();
    Class<PlaceDescription> clazz;
    Transformer<PlaceDescription> transformer = new Transformer(clazz);

    private class PersistGroup extends PlaceDescription {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceDescriptionDao() {
    }

    public Class getClassModel() {
        return Place.class;
    }

    public PlaceDescription getPlaceDescriptionByIdPlace(Integer place_id, String locale) throws PersistException {
        List<PlaceDescription> list;
            Connection conn = connection.retrieve();
            try (PreparedStatement statement = conn.prepareStatement(GET_LOCALE_DESCRIPTIONS_BY_PLACE)) {
                statement.setInt(1, place_id);
                statement.setString(2, locale);
                ResultSet rs = statement.executeQuery();
                list = parseResultSet(rs);
            } catch (Exception e) {
                throw new PersistException(e);
            } finally {
                connection.putback(conn);
            }
            if (list == null || list.size() == 0) {
                throw new PersistException("Record with PK = " + place_id + " not found.");
            }
            if (list.size() > 1) {
                throw new PersistException("Received more than one record.");
            }
            return list.iterator().next();



    }



}
