package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
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
    public static final String getUserByEmailSQL = "SELECT *FROM USER WHERE mail=?";
    public static final String checkMailSQL = "SELECT * FROM USER WHERE mail=?";
    public static final String checkPhoneSQL = "SELECT * FROM USER WHERE phone=?";
    public static final String checkLoginSQL = "SELECT * FROM USER WHERE login=?";
    public static final String WAYS = "select W.id, W.name, W.way_days,W.way_time,P.adress,P.latitude,P.longitude,P.category_id,P.rating,\n" +
            "P.place_time,PI.reference,PD.description, PD.name from user U join user_way UW on U.id=UW.user_id\n" +
            " join way W on UW.way_id=W.id join place_way PW on W.id=PW.id\n" +
            " join place P on PW.place_id= P.id join place_image PI on P.id=PI.place_id\n" +
            " join place_description PD on P.id=PD.description";

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
    public User getUserByEmail(String email) {
        User user = new User();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getUserByEmailSQL)) {
            statement.setString(1, email);
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
            if(email !="" && parseResultSet(rs).isEmpty()){
                return true;
            }
        } catch (SQLException | PersistException e) {
               e.printStackTrace();
        } finally {
            connection.putback(conn);
        }
        return false;
    }

    public boolean checkPhone(String phone) {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(checkPhoneSQL)) {
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            if(phone !="" && parseResultSet(rs).isEmpty()){
                return true;
            }
        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        } finally {
            connection.putback(conn);
        }
        return false;
    }

    public boolean checkLogin(String login) {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(checkLoginSQL)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if(login !="" && parseResultSet(rs).isEmpty()){
                return true;
            }
        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        } finally {
            connection.putback(conn);
        }
        return false;
    }
}
