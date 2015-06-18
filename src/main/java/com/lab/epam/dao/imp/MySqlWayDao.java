package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Way;
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
 * Created by Admin on 10.06.2015.
 */
public class MySqlWayDao extends AbstractJDBCDao<Way, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String GET_WAY_BY_USER_ID = "SELECT w.id, w.rating, w.name, w.visible, w.way_days, w.way_time ,w.deleted FROM way AS w JOIN user_way AS uw JOIN user AS u WHERE uw.user_id = u.id AND uw.way_id = w.id AND uw.deleted='false' AND u.id = ?";
    private static final String DELETE_WAY_BY_USER_ID_WAY_ID = "UPDATE user_way SET deleted = true WHERE user_id = ? AND way_id = ?";

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlWayDao() {
    }

    public Class getClassModel() {
        return Way.class;
    }

    public List<Way> getWaysByUserId(Integer user_id) throws PersistException {

        List<Way> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_WAY_BY_USER_ID)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            loger.info("Get ways from user with id" + user_id + " is succesfull " + rs);
            list = parseResultSet(rs);
            loger.info("Parse result with Transformer is succesfull list = " + list);
            if (list.size() <= 0){
                loger.info("DB has any ways from user with " + user_id + " user_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get ways from user with " + user_id + " user_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;

    }

    public void deleteWaysByUserIdWayId(Integer user_id, Integer way_id) throws PersistException {

        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(DELETE_WAY_BY_USER_ID_WAY_ID)) {
            try {
                statement.setObject(1, user_id);
                statement.setObject(2, way_id);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            loger.warn("Cant delete way from user with " + user_id + " user_id and " + way_id + " way_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }

    }

}


