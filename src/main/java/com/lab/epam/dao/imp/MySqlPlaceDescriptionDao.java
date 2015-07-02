package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
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
import java.util.List;

/**
 * Created by Admin on 12.06.2015.
 */
public class MySqlPlaceDescriptionDao extends AbstractJDBCDao<PlaceDescription, Integer> {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String GET_LOCALE_DESCRIPTIONS_BY_PLACE = "SELECT * FROM place_description WHERE place_id = ? AND locale = ? AND deleted = false";
    private static final String GET_ALL_INFORMATION_BY_PLACE = "SELECT * FROM place_description WHERE place_id = ?";
    private static final String GET_PLACE_BY_SEARCH = "SELECT * FROM place_description WHERE name LIKE '";


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
        return PlaceDescription.class;
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
                loger.warn("Record with PK = " + place_id + " not found.");
                throw new PersistException("Record with PK = " + place_id + " not found.");
            }
            if (list.size() > 1) {
                loger.warn("Received more than one record.");
                throw new PersistException("Received more than one record.");
            }
            return list.iterator().next();



    }

    public List<PlaceDescription> getAllInformationAboutPlace(Integer place_id) throws PersistException {
        loger.info("Method getAllInformationAboutPlace started");
        List<PlaceDescription> list =null;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_ALL_INFORMATION_BY_PLACE)) {
            statement.setInt(1, place_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            loger.warn(e.getMessage());
        } finally {
            connection.putback(conn);
        }

        if (list == null || list.size() == 0) {
            loger.warn("Record with PK = " + place_id + " not found.");
            throw new PersistException("Record with PK = " + place_id + " not found.");
        }
        loger.info("Method getAllInformationAboutPlace ended");
        return list;
    }

    public List<PlaceDescription> getAllPlaceBySearch(String str) throws PersistException {
        loger.info("Method getAllInformationAboutPlace started");
        List<PlaceDescription> list =null;
        Connection conn = connection.retrieve();
        String sql = GET_PLACE_BY_SEARCH + str + "%'";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            loger.warn(e.getMessage());
        } finally {
            connection.putback(conn);
        }

        if (list == null || list.size() == 0) {
            loger.warn("Record with name = " + str + " not found.");
            throw new PersistException("Record with name = " + str + " not found.");
        }
        loger.info("Method getAllPlaceBySearch ended");
        return list;
    }


}
