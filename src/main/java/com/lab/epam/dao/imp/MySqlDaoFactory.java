package com.lab.epam.dao.imp;

import com.lab.epam.dao.DaoFactory;
import com.lab.epam.dao.GenericDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlDaoFactory implements DaoFactory<ConnectionPool> {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private Map<Class, DaoCreator> creators;

    public ConnectionPool getContext() throws PersistException {
        ConnectionPool connection = null;
        connection = ConnectionManager.getConnection();
        return connection;
    }

    @Override
    public GenericDao getDao(ConnectionPool connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        loger.info("Get Dao");
        return creator.create(connection);
    }

    public MySqlDaoFactory() {

       creators = new HashMap<Class, DaoCreator>();
        creators.put(Way.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlWayDao();
            }
        });
        creators.put(Category.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlCategoryDao();
            }
        });

        creators.put(Place.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlPlaceDao();
            }
        });

        creators.put(PlaceResponse.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlPlaceResponseDao();
            }
        });

        creators.put(Role.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlRoleDao();
            }
        });

        creators.put(Status.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlStatusDao();
            }
        });

        creators.put(User.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlUserDao();
            }
        });

        creators.put(WayResponse.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlWayResponseDao();
            }
        });

        creators.put(PlaceDescription.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlPlaceDescriptionDao();
            }
        });

        creators.put(PlaceImage.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlPlaceImageDao();
            }
        });

        creators.put(UserImage.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlUserImageDao();
            }
        });

        creators.put(PlaceRating.class, new DaoCreator<ConnectionPool>() {
            @Override
            public GenericDao create(ConnectionPool connection) {
                return new MySqlPlaceRatingDao();
            }
        });

    }
}
