package com.lab.epam.dao;

import com.lab.epam.persistant.ConnectionPool;

import java.sql.Connection;

/**
 * Created by Admin on 10.06.2015.
 */
public interface DaoFactory<Context> {

    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }

    /** Возвращает подключение к базе данных */
    public ConnectionPool getContext() throws PersistException;

    /** Возвращает объект для управления персистентным состоянием объекта */
    public GenericDao getDao(ConnectionPool connection, Class dtoClass) throws PersistException;
}
