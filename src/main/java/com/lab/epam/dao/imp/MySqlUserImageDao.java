package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.entity.UserImage;
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
 * Created by Admin on 13.06.2015.
 */
public class MySqlUserImageDao extends AbstractJDBCDao<UserImage, Integer> {

    private static final String GET_IMAGE_BY_USER_ID = "SELECT * FROM user_image WHERE user_id = ?";

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private class PersistGroup extends UserImage {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlUserImageDao() {
    }

    public Class getClassModel() {
        return UserImage.class;
    }

    public List<UserImage> getUserImageByUserId(Integer user_id) throws PersistException {
        List<UserImage> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_IMAGE_BY_USER_ID)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            loger.info("Get images from user_images is succesfull " + rs);
            list = parseResultSet(rs);
            loger.info("Parse result with Transformer is succesfull list = " + list);
            if (list.size() <= 0){
                loger.info("DB has any user_images with " + user_id + " user_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get images from user_images with " + user_id + " user_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;



    }


}
