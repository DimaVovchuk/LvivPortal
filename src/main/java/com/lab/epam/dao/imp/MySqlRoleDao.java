package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Role;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import com.lab.epam.transformer.Transformer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlRoleDao extends AbstractJDBCDao<Role, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlRoleDao() {
    }

    public Class getClassModel() {
        return Role.class;
    }


}
