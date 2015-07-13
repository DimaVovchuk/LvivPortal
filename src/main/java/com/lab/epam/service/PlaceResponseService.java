package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlPlaceResponseDao;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.PlaceResponse;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 11.06.2015.
 */
public class PlaceResponseService {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    MySqlPlaceResponseDao mySqlPlaceResponseDao = new MySqlPlaceResponseDao();

    public void create(PlaceResponse object) {
        try {
            mySqlPlaceResponseDao.create(object);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place response");
        }
    }

    public void deleteResponseByUserIdPlaceId(Integer response_id) {
        try {
            mySqlPlaceResponseDao.deleteResponseByUserIdPlaceId(response_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delet response by response_id = " + response_id);
        }
    }

    public PlaceResponse getByPK(Integer key) throws PersistException{
        return mySqlPlaceResponseDao.getByPK(key);
    }

    public void update(PlaceResponse object) throws PersistException{
        mySqlPlaceResponseDao.update(object);
    }

    public void delete(PlaceResponse object) throws PersistException{
        mySqlPlaceResponseDao.delete(object);
    }

    public List<PlaceResponse> getAll() throws PersistException{
        return mySqlPlaceResponseDao.getAll();
    }

    public List<PlaceResponse> getAllWithoutDeleted() throws PersistException{
        return mySqlPlaceResponseDao.getAllWithoutDeleted();
    }

    public List<PlaceResponse> getPlaceResponseByPlace(Integer place_id){
        List<PlaceResponse> placesDescription = null;
        try {
            placesDescription = mySqlPlaceResponseDao.getPlaceResponseByPlace(place_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get response by place response");
        }

        return placesDescription;
    }

}
