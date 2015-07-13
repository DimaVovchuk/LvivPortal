package com.lab.epam.service;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlWayDao;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.Way;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 11.06.2015.
 */
public class WayService {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    MySqlWayDao mySqlWayDao = new MySqlWayDao();
    PlaceService servicePlace = new PlaceService();

    public void create(Way object) {
        try {
            mySqlWayDao.create(object);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create user way");
        }
    }

    public void create(Connection conn, Way object) {
        try {
            mySqlWayDao.create(conn, object);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create user way");
        }
    }

    public Way getByPK(Integer key) {
        Way way = null;
        try {
            way = mySqlWayDao.getByPK(key);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get user way by PK");
        }
        return way;
    }

    public void update(Way object) {
        try {
            mySqlWayDao.update(object);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update place");
        }
    }

    public void delete(Way object) throws PersistException {
        mySqlWayDao.delete(object);
    }

    public List<Way> getAll() throws PersistException {
        return mySqlWayDao.getAll();
    }

    public List<Way> getAllWithoutDeleted() throws PersistException {
        return mySqlWayDao.getAllWithoutDeleted();
    }

    public List<Way> getWaysByUserId(Integer user_id) {
        List<Way> ways = null;
        try {
            ways = mySqlWayDao.getWaysByUserId(user_id);
            ;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get ways by user_id = " + user_id);
        }

        return ways;
    }

    public void deleteWaysByUserIdWayId(Integer user_id, Integer way_id) {
        try {
            mySqlWayDao.deleteWaysByUserIdWayId(user_id, way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delet way by user_id = " + user_id + " way_id " + way_id);
        }
    }

    public Way getLastAdded() {
        Way way = null;
        try {
            way = mySqlWayDao.getLastAdded();
            ;

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get last way");
        }

        return way;
    }

    public Way getLastAdded(Connection conn) {
        Way way = null;
        try {
            way = mySqlWayDao.getLastAdded(conn);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get last way");
        }

        return way;
    }

    public void createUserWay(Integer user_id, Integer way_id, Integer day) {
        try {
            mySqlWayDao.createUserWay(user_id, way_id, day);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create way by user_id = " + user_id + " way_id " + way_id);
        }
    }

    public void createUserWay(Connection conn, Integer user_id, Integer way_id, Integer day) {
        try {
            mySqlWayDao.createUserWay(conn, user_id, way_id, day);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant create way by user_id = " + user_id + " way_id " + way_id);
        }
    }

    public void updateWayDay(Integer user_id, Integer way_id, Integer day) {
        try {
            mySqlWayDao.updateWayDay(user_id, way_id, day);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update way_day by user_id = " + user_id + " way_id " + way_id);
        }
    }

    public void updateWayBeginDate(Integer way_id, Date beginDate) {
        try {
            mySqlWayDao.updateWayBeginDate(way_id, beginDate);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update beginDate" + way_id + "way_id");
        }
    }

    public void updateWayEndDate(Integer way_id, Date endDate) {
        try {
            mySqlWayDao.updateWayEndDate(way_id, endDate);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update endDate by" + " way_id " + way_id);
        }
    }

    public void updateWayRating(Integer way_id, Integer rating) {
        try {
            mySqlWayDao.updateWayRating(way_id, rating);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update endDate by" + " way_id " + way_id);
        }
    }

    public void updateWayIsRecommended(Integer way_id) {
        try {
            mySqlWayDao.updateWayIsRecommended(way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update endDate by" + " way_id " + way_id);
        }
    }

    public void deleteWayIsRecommended(Integer way_id) {
        try {
            mySqlWayDao.deleteWayIsRecommended(way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant delete recommended" + " way_id " + way_id);
        }
    }

    public void updateConfirmWayRecommended(Integer way_id) {
        try {
            mySqlWayDao.updateConfirmWayRecommended(way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update endDate by" + " way_id " + way_id);
        }
    }

    public Integer createUserWay(Way wayNew, Map<Integer, List<Place>> placesDay, Integer userId, Boolean isFull) {

        ConnectionPool connection = ConnectionManager.getConnection();
        Connection conn = connection.retrieve();
        Integer way_id = null;
        try {
            if (isFull) {
                conn.setAutoCommit(false);
                create(conn, wayNew);
                Way way = getLastAdded(conn);
                way_id = way.getId();

                if (way != null) {
                    createUserWay(conn, userId, way.getId(), way.getWay_days());
                    Set<Integer> keys = placesDay.keySet();
                    for (Integer key : keys) {
                        List<Place> places = placesDay.get(key);
                        for (Place place : places) {
//                            if (!place.getVisible()) {
//                                servicePlace.create(conn, place);
//                                place = servicePlace.getPlaceByLongitudeLatitude(conn, place.getLongitude(), place.getLatitude());
//                                loger.info("Create castom place is successfull with id " + place.getId());
//                            }
                            servicePlace.createPlaceWay(conn, place.getId(), way.getId(), key, place.getPlace_time());
                            loger.info("Create place_way is successfull");
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.putback(conn);
        }
        return way_id;
    }

    public List<Way> getAllWayRecomended() {
        List<Way> ways = null;
        try {
            ways = mySqlWayDao.getAllWayRecomended();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant get all places");
        }

        return ways;
    }

    public void setWayIsRecommended(Integer way_id) {
        try {
            mySqlWayDao.setWayIsRecommended(way_id);

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant set is_recommended by way_id " + way_id);
        }
    }

    public List<Way> getAllConfirmRecommendedWay() {
        List<Way> wayList = new ArrayList<>();
        try {
            wayList = mySqlWayDao.getAllConfirmRecommendedWay();

        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Can not get all confirm recommended way.");
        }
        return wayList;
    }
}

