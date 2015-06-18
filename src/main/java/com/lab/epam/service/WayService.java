package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlWayDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Way;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class WayService {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    MySqlWayDao mySqlWayDao = new MySqlWayDao();

    public void create(Way object) throws PersistException {
        mySqlWayDao.create(object);
    }

    public Way getByPK(Integer key) throws PersistException{
        return mySqlWayDao.getByPK(key);
    }

    public void update(Way object) throws PersistException{
        mySqlWayDao.update(object);
    }

    public void delete(Way object) throws PersistException{
        mySqlWayDao.delete(object);
    }

    public List<Way> getAll() throws PersistException{
        return mySqlWayDao.getAll();
    }

    public List<Way> getAllWithoutDeleted() throws PersistException{
        return mySqlWayDao.getAllWithoutDeleted();
    }

    public List<Way> getWaysByUserId(Integer user_id){
        List<Way> ways = null;
        try {
            ways = mySqlWayDao.getWaysByUserId(user_id);;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get ways by user_id = " + user_id);
        }

        return ways;
    }

    public void deleteWaysByUserIdWayId(Integer user_id, Integer way_id){
        try {
            mySqlWayDao.deleteWaysByUserIdWayId(user_id, way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delet way by user_id = " + user_id + " way_id " + way_id);
        }
    }

}

