package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceResponse;
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
public class MySqlPlaceResponseDao extends AbstractJDBCDao<PlaceResponse, Integer> {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final String GET_PLACE_RESPONSE_BY_PLACE_ID = "SELECT * FROM place_response WHERE place_id = ?";

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceResponseDao() {
    }

    public List<PlaceResponse> getPlaceResponseByPlace(Integer place_id) throws PersistException {
        List<PlaceResponse> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_RESPONSE_BY_PLACE_ID)) {
            statement.setInt(1, place_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list.size() <= 0){
                loger.info("DB has any place_response with " + place_id + " place_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get plece_response by place id " + place_id);
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public Class getClassModel() {
        return PlaceResponse.class;
    }
}
