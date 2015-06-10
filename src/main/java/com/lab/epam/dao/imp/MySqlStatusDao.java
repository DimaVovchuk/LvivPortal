package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Status;
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
public class MySqlStatusDao extends AbstractJDBCDao<Status, Integer> {

    Class<Status> clazz;
    Transformer<Status> transformer = new Transformer<>(Status.class);

    private class PersistGroup extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        String tableName = transformer.getTableName();

        return "SELECT * FROM `" + tableName + "`";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO category (category, deleted) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE category SET category= ? deleted = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        String tableName = transformer.getTableName();
        return "UPDATE `" + tableName + "` SET deleted = false WHERE id= ?;";
    }

    @Override
    public Status create(Status obj) throws PersistException {
        return persist((Status)obj);
    }

    public MySqlStatusDao(ConnectionPool connection) {
        super(connection);
    }

    @Override
    protected List<Status> parseResultSet(ResultSet rs) throws PersistException {
        List<Status> result = new LinkedList<Status>();
        result = transformer.rowToObject(rs);
        return result;
    }

    @Override
    protected String prepareStatementForInsert(Status object) throws PersistException{
        List<String> setRow = new ArrayList<>();
        String query = new String();
        try{
            setRow = transformer.setRowInDB(object);
            String tableName = transformer.getTableName();

            query = "INSERT INTO `" + tableName + "` ("
                    + setRow.get(0) + ") VALUES (" + setRow.get(1) + ")";
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return query;
    }

    @Override
    protected String prepareStatementForUpdate(Status object) throws PersistException {
        Map<String, Object> objectColumns = transformer.getObjectColumns(object);
        String field = "";
        for (Map.Entry<String, Object> entry : objectColumns.entrySet()) {
            if (entry.getKey().equals("id")) {
                continue;
            }
            if (entry.getKey().equals("password")) {
                entry.setValue(entry.getValue());
            }
            if (entry.getValue().getClass() == String.class) {
                field += entry.getKey() + " = '" + entry.getValue() + "',";
            } else {
                field += entry.getKey() + " = " + entry.getValue() + ",";
            }
        }
        String substring = field.substring(0, field.length() - 1);
        field = substring;
        String tableName = transformer.getTableName();
        String query = "UPDATE `" + tableName + "` SET " + field
                + " WHERE id = " + object.getId();
        return query;

    }
}
