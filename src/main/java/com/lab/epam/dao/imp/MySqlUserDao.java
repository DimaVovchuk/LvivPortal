package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.User;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {
    public static final String getUserByLoginAndPassSQL = "login=?";
    public static final String checkMailSQL = "email=?";
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
        String sql = getSelectQueryWithOutDeleted();
        sql += getUserByLoginAndPassSQL;
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
        System.out.println("in userDao");
        User user = null;
        String sql = getSelectQueryWithOutDeleted();
        sql += checkMailSQL;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            user = parseResultSet(rs).get(0);
            if (user == null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("check email error");
        } finally {
            connection.putback(conn);
        }
        return false;
    }
}
