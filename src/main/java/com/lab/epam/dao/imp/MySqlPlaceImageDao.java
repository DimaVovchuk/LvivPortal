package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceImage;
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
 * Created by Admin on 12.06.2015.
 */
public class MySqlPlaceImageDao extends AbstractJDBCDao<PlaceImage, Integer> {

    private static final String GET_IMAGE_BY_PLACE_ID = "SELECT * FROM place_image WHERE place_id = ?";

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private class PersistGroup extends PlaceImage {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceImageDao() {
    }

    public Class getClassModel() {
        return PlaceImage.class;
    }

    public PlaceImage getPlaceImageByPlaceId(Integer place_id) throws PersistException {
        List<PlaceImage> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_IMAGE_BY_PLACE_ID)) {
            statement.setInt(1, place_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list.size() <= 0){
                loger.info("DB has any place_images with " + place_id + " place_id");
                return null;
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list.iterator().next();



    }


}
