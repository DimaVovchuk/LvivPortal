package com.lab.epam.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 10.06.2015.
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /** ������� ����� ������ � ��������������� �� ������ */
    public T create(T object) throws PersistException;

    /** ������� ����� ������, ��������������� ������� object */
    public T persist(T object)  throws PersistException;

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public T getByPK(PK key) throws PersistException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(T object) throws PersistException;

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(T object) throws PersistException;

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<T> getAll() throws PersistException;
}
