package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import com.lab.epam.transformer.Transformer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlCategoryDao extends AbstractJDBCDao<Category, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM category WHERE category = ?";

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlCategoryDao() {
    }

    public Class getClassModel() {
        return Category.class;
    }

    public Category getCategoryByName(String categotrName)throws PersistException{
        List<Category> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_CATEGORY_BY_NAME)) {
            statement.setString(1, categotrName);
            ResultSet rs = statement.executeQuery();
            loger.info("Get category with category name " + categotrName + " is succesfull");
            list = parseResultSet(rs);
            loger.info("Parse result with Transformer is succesfull");
            if (list.size() <= 0){
                loger.info("DB has any category with category name " + categotrName);
                return null;
            }
            if (list.size() > 1){
                loger.info("DB has more than one category with category name " + categotrName);
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get category with category name " + categotrName);
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list.iterator().next();
    }
}
