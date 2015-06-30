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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlWayDao extends AbstractJDBCDao<Way, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private static final String GET_WAY_BY_USER_ID = "SELECT w.id, w.rating, w.name, w.visible, w.way_days, w.way_time, w.date_begin, w.date_end, w.deleted FROM way AS w JOIN user_way AS uw JOIN user AS u WHERE uw.user_id = u.id AND uw.way_id = w.id AND uw.deleted='false' AND u.id = ?";
    private static final String DELETE_WAY_BY_USER_ID_WAY_ID = "UPDATE user_way SET deleted = true WHERE user_id = ? AND way_id = ?";
    private static final String GET_LAST_ADDED = "SELECT * FROM way ORDER BY id DESC LIMIT 0,1";
    private static final String CREATE_USER_WAY = "INSERT INTO user_way (user_id, way_id, way_days) VALUES (?,?,?);";
    private static final String UPDATE_WAY_DAY = "UPDATE user_way SET way_days = ? WHERE user_id = ? AND way_id = ?";
    private static final String UPDATE_WAY_BEGIN_DATE = "UPDATE way SET date_begin = ? WHERE id = ?";
    private static final String UPDATE_WAY_END_DATE = "UPDATE way SET date_end = ? WHERE id = ?";

    private class PersistGroup extends Way {
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
            //loger.info("Get ways from user with id" + user_id + " is succesfull " + rs);
            list = parseResultSet(rs);
            //loger.info("Parse result with Transformer is succesfull list = " + list);
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

    public void create(Connection conn, Way way) throws PersistException {

        String sql = prepareStatementForInsert(way);//getCreateQuery();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                loger.info("Create is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
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

    public Way getLastAdded() throws PersistException {

        List<Way> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_LAST_ADDED)) {
            ResultSet rs = statement.executeQuery();
          //  loger.info("Get last way is succesfull ");
            list = parseResultSet(rs);
            //loger.info("Parse result with Transformer is succesfull");
            if (list.size() <= 0){
                loger.info("DB has any ways");
                return null;
            }
            if (list.size() > 1){
                loger.info("DB has more than one last way");
            }
        } catch (Exception e) {
            loger.warn("Cant last way");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list.iterator().next();

    }

    public Way getLastAdded(Connection conn) throws PersistException {

        List<Way> list;
        try (PreparedStatement statement = conn.prepareStatement(GET_LAST_ADDED)) {
            ResultSet rs = statement.executeQuery();
            //  loger.info("Get last way is succesfull ");
            list = parseResultSet(rs);
            //loger.info("Parse result with Transformer is succesfull");
            if (list.size() <= 0){
                loger.info("DB has any ways");
                return null;
            }
            if (list.size() > 1){
                loger.info("DB has more than one last way");
            }
        } catch (Exception e) {
            loger.warn("Cant last way");
            throw new PersistException(e);
        }
        return list.iterator().next();

    }

    public void createUserWay(Integer user_id, Integer way_id, Integer day) throws PersistException {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(CREATE_USER_WAY)) {
            statement.setInt(1, user_id);
            statement.setInt(2, way_id);
            statement.setInt(3, day);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                //   System.out.println("Create is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

    public void createUserWay(Connection conn, Integer user_id, Integer way_id, Integer day) throws PersistException {
        try (PreparedStatement statement = conn.prepareStatement(CREATE_USER_WAY)) {
            statement.setInt(1, user_id);
            statement.setInt(2, way_id);
            statement.setInt(3, day);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                //   System.out.println("Create is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public void updateWayDay(Integer user_id, Integer way_id, Integer day) throws PersistException {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_WAY_DAY)) {
            statement.setInt(2, user_id);
            statement.setInt(3, way_id);
            statement.setInt(1, day);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

    public void updateWayBeginDate(Integer way_id, Date beginDate) throws PersistException {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_WAY_BEGIN_DATE)) {
            statement.setDate(1, beginDate);
            statement.setInt(2, way_id);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

    public void updateWayEndDate(Integer way_id, Date endDate) throws PersistException {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_WAY_END_DATE)) {
            statement.setDate(1, endDate);
            statement.setInt(2, way_id);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

}


