package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.persistant.ConnectionPool;
import com.lab.epam.transformer.Transformer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlCategoryDao extends AbstractJDBCDao<Category, Integer> {

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlCategoryDao() {
    }

    public MySqlCategoryDao(ConnectionPool connection) {
        super(connection);
    }

    public Class getClassModel() {
        return Category.class;
    }
}
