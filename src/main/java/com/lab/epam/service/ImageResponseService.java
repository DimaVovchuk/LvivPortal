package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlImageResponseDao;
import com.lab.epam.dao.imp.MySqlPlaceResponseDao;
import com.lab.epam.entity.ImageResponse;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public class ImageResponseService {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    MySqlImageResponseDao mySqlImageResponseDao = new MySqlImageResponseDao();

    public void create(ImageResponse object) {
        try {
            mySqlImageResponseDao.create(object);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place response");
        }
    }

    public ImageResponse getByPK(Integer key) throws PersistException{
        return mySqlImageResponseDao.getByPK(key);
    }

    public void update(ImageResponse object) throws PersistException{
        mySqlImageResponseDao.update(object);
    }

    public void delete(ImageResponse object) throws PersistException{
        mySqlImageResponseDao.delete(object);
    }

    public List<ImageResponse> getAll() throws PersistException{
        return mySqlImageResponseDao.getAll();
    }

    public List<ImageResponse> getAllWithoutDeleted() throws PersistException{
        return mySqlImageResponseDao.getAllWithoutDeleted();
    }

    public List<ImageResponse> getImageResponseByImage(Integer image_id){
        List<ImageResponse> placesDescription = null;
        try {
            placesDescription = mySqlImageResponseDao.getImageResponseByImage(image_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get response by image response");
        }

        return placesDescription;
    }

}
