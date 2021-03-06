package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public static final String getUserByLoginAndPassSQL = "SELECT *FROM USER WHERE login=?";
    public static final String getUserByEmailSQL = "SELECT *FROM USER WHERE mail=?";
    public static final String getUserByPhoneSQL = "SELECT *FROM USER WHERE phone=?";
    public static final String checkMailSQL = "SELECT * FROM USER WHERE mail=?";
    public static final String checkPhoneSQL = "SELECT * FROM USER WHERE phone=?";
    public static final String checkLoginSQL = "SELECT * FROM USER WHERE login=?";
    public static final String getRoleID = "SELECT role_id FROM USER WHERE login=?";
    public static final String getUserByRole = "SELECT * FROM USER WHERE role_id=? AND deleted = false AND status_id = 1";
    public static final String getUserByVkId = "SELECT * FROM USER WHERE vk_id=?";
    public static final String getQuantityOfAllUsers = "SELECT COUNT(*) FROM user;";
    public static final String getQuantityUsersByRoleId = "SELECT COUNT(role_id) FROM user where role_id = ? GROUP BY role_id";
    public static final String getQuantityUsersByStatusId = "SELECT COUNT(status_id) FROM user where status_id = ? GROUP BY status_id";
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

    public Integer getQuantityOfAllUsers() {
        Integer quantity = 0;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getQuantityOfAllUsers)) {
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        loger.info("getQuantityOfAllUsers method");

        return quantity;
    }

    public Integer getQuantityUsersByRoleId(Integer roleID) {
        Integer quantity = 0;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getQuantityUsersByRoleId)) {
            statement.setInt(1, roleID);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        loger.info("getQuantityUsersByRoleId method");

        return quantity;
    }

    public Integer getQuantityUsersByStatusId(Integer statusID) {
        Integer quantity = 0;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getQuantityUsersByStatusId)) {
            statement.setInt(1, statusID);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        loger.info("getQuantityUsersByStatusId method");

        return quantity;
    }

    public List<User> getUserByRole(Integer statusID){
        List<User> userList = new ArrayList<>();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getUserByRole)) {
            statement.setInt(1, statusID);
            ResultSet rs = statement.executeQuery();
            userList = parseResultSet(rs);
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        loger.info("getUserByRole method");

        return userList;
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
        loger.info("getUserByLogin method");

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
        loger.info("getUserByEmail method");

        return user;
    }

    public User getUserByVkId(int vk_id) {
        User user = new User();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getUserByVkId)) {
            statement.setInt(1, vk_id);
            ResultSet rs = statement.executeQuery();
            user = parseResultSet(rs).get(0);
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        loger.info("getUserByVkId method");

        return user;
    }

    public User getUserByVkId(String vk_id) {
        User user = new User();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getUserByVkId)) {
            statement.setString(1, vk_id);
            ResultSet rs = statement.executeQuery();
            user = parseResultSet(rs).get(0);
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        loger.info("getUserByVkId method");

        return user;
    }

    public User getUserByPhone(String phone) {
        User user = new User();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getUserByPhoneSQL)) {
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            user = parseResultSet(rs).get(0);
        } catch (Exception e) {
        } finally {
            connection.putback(conn);
        }
        loger.info("getUserByPhone method");

        return user;
    }

    public boolean checkEmail(String email) {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(checkMailSQL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (email != "" && parseResultSet(rs).isEmpty()) {
                return true;
            }
            loger.info("checkEmail method");

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
            if (phone != "" && parseResultSet(rs).isEmpty()) {
                return true;
            }
            loger.info("checkPhone method");

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
            if (login != "" && parseResultSet(rs).isEmpty()) {
                return true;
            }
            loger.info("checkLogin method");

        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        } finally {
            connection.putback(conn);
        }
        return false;
    }

    public Integer getRoleID(String login) {
        Integer roleID = null;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(getRoleID)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                roleID = rs.getInt("role_id");
            }
            loger.info("getRoleID method");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.putback(conn);
        }
        return roleID;
    }
}