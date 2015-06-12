package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceResponse;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import com.lab.epam.transformer.Transformer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlPlaceResponseDao extends AbstractJDBCDao<PlaceResponse, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();

    private class PersistGroup extends PlaceResponse {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceResponseDao() {
    }

    public Class getClassModel() {
        return PlaceResponse.class;
    }
}
