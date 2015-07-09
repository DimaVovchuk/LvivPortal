package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.ImageResponse;
import com.lab.epam.entity.PlaceResponse;
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
 * Created by Admin on 08.07.2015.
 */
public class MySqlImageResponseDao  extends AbstractJDBCDao<ImageResponse, Integer> {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final String GET_IMAGE_RESPONSE_BY_IMAGE_ID = "SELECT * FROM image_response WHERE image_id = ?";

    private class PersistGroup extends ImageResponse {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlImageResponseDao() {
    }

    public List<ImageResponse> getImageResponseByImage(Integer image_id) throws PersistException {
        List<ImageResponse> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_IMAGE_RESPONSE_BY_IMAGE_ID)) {
            statement.setInt(1, image_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list.size() <= 0){
                   loger.info("DB has any image_response with " + image_id + " image_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get image_response by image_id " + image_id);
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public Class getClassModel() {
        return ImageResponse.class;
    }
}

