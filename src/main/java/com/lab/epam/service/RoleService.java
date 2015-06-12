package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlRoleDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Role;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class RoleService {

    MySqlRoleDao mySqlRoleDao = new MySqlRoleDao();

    public void create(Role object) throws PersistException {
        mySqlRoleDao.create(object);
    }

    public Role getByPK(Integer key) throws PersistException{
        return mySqlRoleDao.getByPK(key);
    }

    public void update(Role object) throws PersistException{
        mySqlRoleDao.update(object);
    }

    public void delete(Role object) throws PersistException{
        mySqlRoleDao.delete(object);
    }

    public List<Role> getAll() throws PersistException{
        return mySqlRoleDao.getAll();
    }

    public List<Role> getAllWithoutDeleted() throws PersistException{
        return mySqlRoleDao.getAllWithoutDeleted();
    }
}
