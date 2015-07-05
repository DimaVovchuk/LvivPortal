package com.lab.epam.dao.imp;

import com.lab.epam.dao.AbstractJDBCDao;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.FavoritePlacesByRating;
import com.lab.epam.entity.Place;
import com.lab.epam.helper.ClassName;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10.06.2015.
 */
public class MySqlPlaceDao extends AbstractJDBCDao<Place, Integer> {

    ConnectionPool connection = ConnectionManager.getConnection();
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private static final String GET_PLACE_BY_CATEGORY = "SELECT * FROM place WHERE deleted=false AND visible=true AND category_id = ?";
    private static final String GET_PLACE_BY_CATEGORY_RECOMENDED = "SELECT * FROM place WHERE recomended=true AND deleted=false AND visible=true AND category_id = ?";
    private static final String GET_PLACE_RECOMENDED = "SELECT * FROM place WHERE recomended=true AND deleted=false AND visible=true";
    private static final String GET_PLACE_VISIBLE = "SELECT * FROM place WHERE deleted=false AND visible=true";
    private static final String GET_PLACE_BY_USER_ID = "SELECT p.id, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.place_time, p.deleted, p.recomended, p.custom,p.recom_time FROM place AS p JOIN user_place AS up JOIN user AS u WHERE up.user_id = u.id AND up.deleted = false AND up.place_id = p.id AND u.id = ?";
    private static final String GET_PLACE_BY_WAY_ID_DAY_NUMBER = "SELECT p.id, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.place_time, p.deleted, p.recomended, p.custom,p.recom_time FROM place AS p JOIN place_way AS pw JOIN way AS w WHERE pw.way_id = w.id AND pw.place_id = p.id AND pw.deleted = false AND w.id = ? AND pw.day_number = ?";
    private static final String GET_PLACE_BY_WAY_ID = "SELECT p.id, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.place_time, p.deleted, p.recomended, p.custom,p.recom_time FROM place AS p JOIN place_way AS pw JOIN way AS w WHERE pw.way_id = w.id AND pw.place_id = p.id AND w.id = ? AND pw.deleted = false";
    private static final String DELETE_PLACE_BY_USER_ID_PLACE_ID = "UPDATE user_place SET deleted = true WHERE user_id = ? AND place_id = ?";
    private static final String GET_PLACE_BY_LATITUDE_LONGITUDE = "SELECT * FROM place WHERE longitude = ? AND latitude = ?";
    private static final String CREATE_PLACE_WAY = "INSERT INTO place_way (place_id, way_id, day_number, time) VALUES (?,?,?,?);";
    private static final String CREATE_USER_PLACE = "INSERT INTO user_place (place_id, user_id) VALUES (?,?);";
    private static final String GET_PLACE_ID_BY_USER_ID = "SELECT id FROM user_place WHERE place_id = ? AND user_id = ? AND deleted=false";
    private static final String DELETE_PLACE_BY_WAY_ID_PLACE_ID = "UPDATE place_way SET deleted = true WHERE way_id = ? AND place_id = ? AND day_number = ?";
    private static final String DELETE_PLACE_BY_WAY_ID_DAY_NUMBER = "UPDATE place_way SET deleted = true WHERE way_id = ? AND day_number = ?";
    private static final String GET_ALL_VISIBLE_CUSTOM_PLACE = "SELECT p.id, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.place_time, p.deleted, p.recomended, p.custom,p.recom_time \n" +
            "FROM place AS p JOIN user_place AS up JOIN user AS u WHERE up.user_id = u.id AND up.place_id = p.id and p.custom = true and p.deleted=false and up.deleted =false AND u.id = ?";
    private static final String GET_ALL_VISIBLE_FAVOR_PLACE = "SELECT p.id, p.latitude, p.longitude, p.category_id, p.rating, p.visible, p.place_time, p.deleted, p.recomended, p.custom,p.recom_time\n" +
            "FROM place AS p JOIN user_place AS up JOIN user AS u WHERE up.user_id = u.id AND up.place_id = p.id AND p.custom = false AND\n" +
            " p.visible = true AND p.deleted=false and up.deleted =false AND u.id = ?";
    private static final String GET_PLACE_BY_RATING = "SELECT up.*, COUNT(place_id) FROM user_place up JOIN place p ON up.place_id=p.id\n" +
            " WHERE up.deleted=false AND p.deleted = false AND p.visible = true GROUP BY place_id ORDER BY COUNT(place_id) DESC;";

    private class PersistGroup extends Place {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPlaceDao() {
    }

    public Class getClassModel() {
        return Place.class;
    }

    public List<FavoritePlacesByRating> getPlacesByRating() throws PersistException {
        List<FavoritePlacesByRating> FavoritePlacesByRatinList = new ArrayList<>();
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_RATING)) {
            ResultSet rs = statement.executeQuery();
            try {
                while (rs.next()) {
                    FavoritePlacesByRating fpbr = new FavoritePlacesByRating();
                    fpbr.setId(rs.getInt("id"));
                    fpbr.setUser_id(rs.getInt("user_id"));
                    fpbr.setPlace_id(rs.getInt("place_id"));
                    fpbr.setDeleted(rs.getBoolean("deleted"));
                    fpbr.setCount(rs.getInt(5));
                    FavoritePlacesByRatinList.add(fpbr);
                }
            } catch (SQLException ex) {
                loger.error(ex.getMessage());
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return FavoritePlacesByRatinList;
    }

    public List<Place> getAllVisibleUserCustomPlace(Integer usedID) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_ALL_VISIBLE_CUSTOM_PLACE)) {
            statement.setInt(1, usedID);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public List<Place> getAllVisibleUserFavorPlace(Integer usedID) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_ALL_VISIBLE_FAVOR_PLACE)) {
            statement.setInt(1, usedID);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public Integer createAndReturnIndex(Place place) {
        Connection connect = connection.getConnection();
        Integer index = -1;
        PreparedStatement ps = null;
        PreparedStatement psGetId = null;
        ResultSet rsId = null;
        String sqlQuery = "INSERT INTO place (latitude,longitude,category_id,rating,visible,place_time,deleted,recomended,custom) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        String sqlQueryGetId = "SELECT id FROM place WHERE id = LAST_INSERT_ID();";
        try {
            connect.setAutoCommit(false);
            ps = connect.prepareStatement(sqlQuery);
            psGetId = connect.prepareStatement(sqlQueryGetId);
            ps.setString(1, place.getLatitude());
            ps.setString(2, place.getLongitude());
            ps.setInt(3, place.getCategory_id());
            ps.setInt(4, place.getRating());
            ps.setBoolean(5, place.getVisible());
            ps.setInt(6, place.getPlace_time());
            ps.setBoolean(7, place.getDeleted());
            ps.setBoolean(8, place.getRecomended());
            ps.setBoolean(9, place.getCustom());
            ps.executeUpdate();

            loger.info("before executeQuery");
            rsId = psGetId.executeQuery();
            loger.info("after executeQuery" + rsId.toString());
            if (rsId.next()) {
                index = rsId.getInt("id");
            }
            loger.info("index " + index);
            connect.commit();
        } catch (SQLException e) {
            loger.error(e.getMessage());
        }
        try {
            connect.setAutoCommit(true);
        } catch (SQLException ex) {
            loger.error(ex.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return index;
    }

    public void create(Connection conn, Place place) throws PersistException {

        String sql = prepareStatementForInsert(place);//getCreateQuery();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                loger.info("Create is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public List<Place> getAllPlaceRecomended() throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_RECOMENDED)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public List<Place> getAllPlaceVisible() throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_VISIBLE)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;


    }

    public List<Place> getPlaceByCategory(Integer category_id) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_CATEGORY)) {
            statement.setInt(1, category_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;


    }

    public List<Place> getPlaceByCategoryRecomended(Integer category_id) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_CATEGORY_RECOMENDED)) {
            statement.setInt(1, category_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;


    }

    public List<Place> getPlaceByUserId(Integer user_id) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_USER_ID)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list.size() <= 0) {
                loger.info("DB has any place from user with " + user_id + " user_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places from user with " + user_id + " user_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }


    public List<Place> getPlaceByWayId(Integer way_id) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_WAY_ID)) {
            statement.setInt(1, way_id);
            ResultSet rs = statement.executeQuery();
            // loger.info("Get places from way with id" + way_id + " is succesfull ");
            list = parseResultSet(rs);
            if (list.size() <= 0) {
                loger.info("DB has any place from way with " + way_id + " way_id");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places from way with " + way_id + " way_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public List<Place> getPlaceByWayIdDayNumber(Integer way_id, Integer day_number) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_WAY_ID_DAY_NUMBER)) {
            statement.setInt(1, way_id);
            statement.setInt(2, day_number);
            ResultSet rs = statement.executeQuery();
            // loger.info("Get places from way with id" + way_id + " is succesfull ");
            list = parseResultSet(rs);
            if (list.size() <= 0) {
                loger.info("DB has any place from way with " + way_id + " way_id " + day_number + " day_number");
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places from way with " + way_id + " way_id " + day_number + " day_number");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list;
    }

    public void deletePlaceByUserIdPlaceId(Integer user_id, Integer place_id) throws PersistException {

        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(DELETE_PLACE_BY_USER_ID_PLACE_ID)) {
            try {
                statement.setObject(1, user_id);
                statement.setObject(2, place_id);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            loger.warn("Cant delete way from user with " + user_id + " user_id and " + place_id + " place_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }

    }

    public Place getPlaceByLongitudeLatitude(String longitude, String latitude) throws PersistException {
        List<Place> list;
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_LATITUDE_LONGITUDE)) {
            statement.setString(1, longitude);
            statement.setString(2, latitude);
            ResultSet rs = statement.executeQuery();
            //  loger.info("Get places with longitude " + longitude + " and latitude " + latitude + " is succesfull ");
            list = parseResultSet(rs);
            //loger.info("Parse result with Transformer is succesfull");
            if (list.size() <= 0) {
                loger.info("DB has any place with longitude " + longitude + " and latitude " + latitude);
                return null;
            }
            if (list.size() > 1) {
                loger.info("DB has more than one place with longitude " + longitude + " and latitude " + latitude);
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places with longitude " + longitude + " and latitude " + latitude);
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
        return list.iterator().next();
    }

    public Place getPlaceByLongitudeLatitude(Connection conn, String longitude, String latitude) throws PersistException {
        List<Place> list;
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_BY_LATITUDE_LONGITUDE)) {
            statement.setString(1, longitude);
            statement.setString(2, latitude);
            ResultSet rs = statement.executeQuery();
            //  loger.info("Get places with longitude " + longitude + " and latitude " + latitude + " is succesfull ");
            list = parseResultSet(rs);
            //loger.info("Parse result with Transformer is succesfull");
            if (list.size() <= 0) {
                loger.info("DB has any place with longitude " + longitude + " and latitude " + latitude);
                return null;
            }
            if (list.size() > 1) {
                loger.info("DB has more than one place with longitude " + longitude + " and latitude " + latitude);
                return null;
            }
        } catch (Exception e) {
            loger.warn("Cant get places with longitude " + longitude + " and latitude " + latitude);
            throw new PersistException(e);
        }
        return list.iterator().next();
    }

    public void createPlaceWay(Integer place_id, Integer way_id, Integer day, Integer time) throws PersistException {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(CREATE_PLACE_WAY)) {
            statement.setInt(1, place_id);
            statement.setInt(2, way_id);
            statement.setInt(3, day);
            statement.setInt(4, time);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                //   System.out.println("Create is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

    public void createPlaceWay(Connection conn, Integer place_id, Integer way_id, Integer day, Integer time) throws PersistException {
        try (PreparedStatement statement = conn.prepareStatement(CREATE_PLACE_WAY)) {
            statement.setInt(1, place_id);
            statement.setInt(2, way_id);
            statement.setInt(3, day);
            statement.setInt(4, time);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                //   System.out.println("Create is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public void createPlaceUser(Integer place_id, Integer user_id) throws PersistException {
        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(CREATE_USER_PLACE)) {
            statement.setInt(1, place_id);
            statement.setInt(2, user_id);

            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            } else {
                //System.out.println("Create user_place is succesfule");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

    public Integer getPlaceByUserIdPlaceId(Integer place_id, Integer user_id) throws PersistException {
        Connection conn = connection.retrieve();
        Integer id;
        try (PreparedStatement statement = conn.prepareStatement(GET_PLACE_ID_BY_USER_ID)) {
            statement.setInt(1, place_id);
            statement.setInt(2, user_id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return 1;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }
    }

    public void deletePlaceByWayIdPlaceId(Integer way_id, Integer place_id, Integer day_number) throws PersistException {

        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(DELETE_PLACE_BY_WAY_ID_PLACE_ID)) {
            try {
                statement.setObject(1, way_id);
                statement.setObject(2, place_id);
                statement.setObject(3, day_number);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            loger.warn("Cant delete place from place_way with " + way_id + " way_id and " + place_id + " place_id");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }

    }


    public void deletePlaceByWayIdDayNumber(Integer way_id, Integer day_number) throws PersistException {

        Connection conn = connection.retrieve();
        try (PreparedStatement statement = conn.prepareStatement(DELETE_PLACE_BY_WAY_ID_DAY_NUMBER)) {
            try {
                statement.setObject(1, way_id);
                statement.setObject(2, day_number);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();

        } catch (Exception e) {
            loger.warn("Cant delete place from place_way with " + way_id + " way_id and " + day_number + " day_number");
            throw new PersistException(e);
        } finally {
            connection.putback(conn);
        }

    }

}

