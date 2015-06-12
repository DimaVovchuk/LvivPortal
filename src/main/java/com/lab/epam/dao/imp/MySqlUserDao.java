package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.User;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {
    public static final String getUserByLoginAndPassSQL = "SELECT *FROM USER WHERE login=?";
    public static final String checkMailSQL = "SELECT *FROM USER WHERE mail=?";
    private ConnectionPool connection = ConnectionManager.getConnection();

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlUserDao() {
    }

    public Class getClassModel() {
        return User.class;
    }


    public User getUserByLogin(String login) {
        User user = new User();
        String sql = getUserByLoginAndPassSQL;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            user = parseResultSet(rs).get(0);
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        return user;
    }

    public boolean checkEmail(String email) {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(checkMailSQL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return rs.wasNull();
        } catch (SQLException e) {
        } finally {
            connection.putback(conn);
        }
        return false;
    }
}
