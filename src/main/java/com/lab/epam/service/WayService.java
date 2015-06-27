package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlWayDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.Way;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class WayService {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    MySqlWayDao mySqlWayDao = new MySqlWayDao();

    public void create(Way object){
        try {
            mySqlWayDao.create(object);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create user way");
        }
    }

    public Way getByPK(Integer key){
        Way way = null;
        try {
            way = mySqlWayDao.getByPK(key);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get user way by PK");
        }
        return way;
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

    public Way getLastAdded(){
        Way way = null;
        try {
            way = mySqlWayDao.getLastAdded();;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get last way");
        }

        return way;
    }

    public void createUserWay(Integer user_id, Integer way_id, Integer day){
        try {
            mySqlWayDao.createUserWay(user_id, way_id, day);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create way by user_id = " + user_id + " way_id " + way_id);
        }
    }

    public void updateWayDay(Integer user_id, Integer way_id, Integer day){
        try {
            mySqlWayDao.updateWayDay(user_id, way_id, day);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update way_day by user_id = " + user_id + " way_id " + way_id);
        }
    }

    public void updateWayBeginDate(Integer way_id, Date beginDate){
        try {
            mySqlWayDao.updateWayBeginDate(way_id, beginDate);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update beginDate" +  way_id  + "way_id");
        }
    }

    public void updateWayEndDate(Integer way_id, Date endDate){
        try {
            mySqlWayDao.updateWayEndDate(way_id, endDate);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update endDate by" + " way_id " + way_id);
        }
    }

}

