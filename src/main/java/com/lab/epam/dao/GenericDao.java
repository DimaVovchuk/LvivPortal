package com.lab.epam.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 10.06.2015.
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    public void create(T object) throws PersistException;

    public T getByPK(PK key) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

    public List<T> getAll() throws PersistException;

    public List<T> getAllWithoutDeleted() throws PersistException;
}
