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
    private ConnectionPool connection = ConnectionManager.getConnection();

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlUserDao() {
    }

    public MySqlUserDao(ConnectionPool connection) {
        super(connection);
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
            user = (User) parseResultSet(rs).get(0);

        } catch (Exception e) {

        } finally {
            connection.putback(conn);
        }
        return user;
    }
}
