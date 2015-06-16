package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceRating;
import com.lab.epam.entity.Role;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Admin on 15.06.2015.
 */
public class MySqlPlaceRatingDao extends AbstractJDBCDao<PlaceRating, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private class PersistGroup extends PlaceRating {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceRatingDao() {
    }

    public Class getClassModel() {
        return PlaceRating.class;
    }


}
