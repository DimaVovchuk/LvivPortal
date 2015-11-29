package com.lab.epam.dao;
import com.lab.epam.dao.imp.MySqlDaoFactory;
import com.lab.epam.entity.Decoder;
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
import java.util.List;
import java.util.Map;

public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK>, ClassNameInterface {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private ConnectionPool connection;
    private Decoder dec;

    Class<T> clazz;
    Transformer<T> transformer;

    public String getSelectQuery(){
        String tableName = transformer.getTableName();
        loger.info("getSelectQuery method");
        return "SELECT * FROM `" + tableName + "` WHERE deleted= false;";
    }

    public String getSelectQueryWithOutDeleted(){
        String tableName = transformer.getTableName();
        loger.info("getSelectQueryWithOutDeleted method");
        return "SELECT * FROM `" + tableName + "`";
    }

    public String getDeleteQuery() {
        String tableName = transformer.getTableName();
        loger.info("getDeleteQuery method");
        return "UPDATE `" + tableName + "` SET deleted = true WHERE id= ?;";
    }


    protected List<T> parseResultSet(ResultSet rs) throws PersistException{
        List<T> result = new ArrayList<>();
        result = transformer.rowToObject(rs);
        loger.info("parseResultSet method");
        return result;
    }
    protected String prepareStatementForInsert(T object) throws PersistException{
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
        loger.info("prepareStatementForInsert method");
        return query;
    }
    protected String prepareStatementForUpdate(T object) throws PersistException{
        Map<String, Object> objectColumns = transformer.getObjectColumns(object);
        String field = "";
        for (Map.Entry<String, Object> entry : objectColumns.entrySet()) {
            if (entry.getKey().equals("id")) {
                continue;
            }
            if (entry.getKey().equals("password")) {
                entry.setValue(entry.getValue());
            }

            if (entry.getValue() != null && entry.getValue().getClass() == String.class) {
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

        loger.info("prepareStatementForUpdate method");
        return query;

    }

    @Override
    public void create(T object) throws PersistException {
        T persistInstance;
        String sql = prepareStatementForInsert(object);//getCreateQuery();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
           // prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                loger.info("Create is successful");
            }
            loger.info("create method");
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }


    }

    @Override
    public T getByPK(Integer key) throws PersistException {
        List<T> list;
        String sql = getSelectQueryWithOutDeleted();
        sql += " WHERE id = ?";
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        loger.info("getByPK method");
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = prepareStatementForUpdate(object);
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
            loger.info("update method");
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }

    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
            loger.info("delete method");
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

    @Override
    public List<T> getAllWithoutDeleted() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        loger.info("getAllWithoutDeleted method");
        return list;
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQueryWithOutDeleted();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        loger.info("getAll method");
        return list;
    }

    public AbstractJDBCDao() {
        MySqlDaoFactory daoFactory  = new MySqlDaoFactory();;
        connection  = ConnectionManager.getConnection();
        clazz = this.getClassModel();
        transformer = new Transformer(clazz);
    }



}
