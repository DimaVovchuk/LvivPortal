package com.lab.epam.service;

import com.lab.epam.dao.GenericDao;
import com.lab.epam.dao.Identified;
import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlDaoFactory;
import com.lab.epam.entity.PlaceResponse;
import com.lab.epam.persistant.ConnectionPool;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class Service<T extends Identified<PK>, PK extends Serializable> {

    MySqlDaoFactory daoFactory;
    ConnectionPool con;
    GenericDao<T,PK> dao;

    public Service(Class clazz){
        daoFactory = new MySqlDaoFactory();
        try {
            ConnectionPool con = daoFactory.getContext();
            dao = daoFactory.getDao(con, clazz);
        }catch(PersistException e){
            e.printStackTrace();
        }

    }

    public T create(T object) throws PersistException{
        return dao.create(object);
    }



    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public T getByPK(PK key) throws PersistException{
        return dao.getByPK(key);
    }

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(T object) throws PersistException{
        dao.update(object);
    }

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(T object) throws PersistException{
        dao.delete(object);
    }

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<T> getAll() throws PersistException{
        return dao.getAll();
    }

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ ���� �������� */
    public List<T> getAllWithoutDeleted() throws PersistException{
        return dao.getAllWithoutDeleted();
    }
}


