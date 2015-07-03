package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceImageDao;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12.06.2015.
 */
public class PlaceImageService {

    MySqlPlaceImageDao mySqlPlaceImageDao = new MySqlPlaceImageDao();

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void create(PlaceImage object) {
        try {
            mySqlPlaceImageDao.create(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create place images");
        }
    }

    public PlaceImage getByPK(Integer key) {
        PlaceImage placeImage = null;
        try {
            placeImage = mySqlPlaceImageDao.getByPK(key);
            ;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get place images by id");
        }
        return placeImage;
    }

    public void update(PlaceImage object) {
        try {
            mySqlPlaceImageDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update place images");
        }
    }

    public void delete(PlaceImage object) {
        try {
            mySqlPlaceImageDao.delete(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delete place images");
        }
    }

    public List<PlaceImage> getAll() {
        List<PlaceImage> placeImage = null;
        try {
            placeImage = mySqlPlaceImageDao.getAll();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place images");
        }

        return placeImage;
    }

    public List<PlaceImage> getAllWithoutDeleted() {
        List<PlaceImage> placeImage = null;
        List<PlaceImage> referenceList = null;
        try {
            placeImage = mySqlPlaceImageDao.getAllWithoutDeleted();
            ;
            referenceList = new ArrayList<>();
            for (int index = 0; index < placeImage.size(); index++) {
                if (placeImage.get(index).getDeleted() == false)
                    referenceList.add(placeImage.get(index));
            }
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all place images");
        }

        return referenceList;
    }

    public PlaceImage getPlaceImageByPlaceId(Integer place_id) {
        PlaceImage placeImage = null;
        try {
            placeImage = mySqlPlaceImageDao.getPlaceImageByPlaceId(place_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get images from DB");
        }
        return placeImage;
    }

    public List<PlaceImage> getAllPlaceImageByPlaceId(Integer place_id) {
        List<PlaceImage> placeImageList = null;
        List<PlaceImage> referenceList = null;
        try {
            placeImageList = mySqlPlaceImageDao.getAllPlaceImageByPlaceId(place_id);
            referenceList = new ArrayList<>();
            if (placeImageList != null) {
                for (int index = 0; index < placeImageList.size(); index++) {
                    if (placeImageList.get(index).getDeleted() == false)
                        referenceList.add(placeImageList.get(index));
                }
            }else{
                return null;
            }
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get images from DB");
        }
        return referenceList;
    }
}
