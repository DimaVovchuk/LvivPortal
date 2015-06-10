package com.lab.epam.dao;

import com.lab.epam.persistant.ConnectionPool;

/**
 * Created by Admin on 10.06.2015.
 */
public interface DaoFactory<Context> {

    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }

    /** ���������� ����������� � ���� ������ */
    public Context getContext() throws PersistException;

    /** ���������� ������ ��� ���������� ������������� ���������� ������� */
    public GenericDao getDao(Context context, Class dtoClass) throws PersistException;
}
