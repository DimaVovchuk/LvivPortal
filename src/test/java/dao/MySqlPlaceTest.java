package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.entity.Place;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleguk on 07.07.2015.
 */
public class MySqlPlaceTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlPlaceDao dao;
    private static final Integer WAY_ID = 1;
    private static final Integer DAY_NUM = 1;
    private static final Integer TIME = 15;
    private static final Integer USER_ID = 5;
    private static final Integer ID = 38;
    private static final String LATITUDE = "78.84438";
    private static final String LONGITUDE = "61.039002";
    private static final Boolean VISIBLE = true;
    private static final Integer CATEGORY_ID = 1;
    private static final Integer RATING = 0;
    private static final Integer PLACE_TIME = 0;
    private static final Boolean RECOMENDED = true;
    private static final Boolean CUSTOM = false;
    private static final Boolean DELETED = false;
    private static final Integer RECOM_TIME = 15;
    private static final Boolean IS_RECOMMENDED = false;

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlPlaceDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testCreate() throws Exception {
        List list = dao.getAll();
        Place place = new Place(ID, LATITUDE, LONGITUDE, VISIBLE, CATEGORY_ID, RATING, PLACE_TIME, RECOMENDED, CUSTOM, DELETED, RECOM_TIME, IS_RECOMMENDED);
        place.setId(list.size() + 5);
        dao.create(place);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(place);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
    }

    @Test public void testGetByPK() throws Exception
    {
        Place place = dao.getByPK(ID);
        Assert.assertNotNull(place);
    }

    @Test
    public void testDelete() throws Exception {
        List list = dao.getAll();
        Place place = new Place(ID, LATITUDE, LONGITUDE, VISIBLE, CATEGORY_ID, RATING, PLACE_TIME, RECOMENDED, CUSTOM, DELETED, RECOM_TIME, IS_RECOMMENDED);
        place.setId(list.size() + 5);
        dao.create(place);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(place);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int newSize = list.size();
        Assert.assertEquals(1, oldSize - newSize);
    }

    @Test
    public void testGetAll() throws Exception {
        List list = dao.getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetAllWithOutDeleted() throws Exception {
        List listOut = dao.getAllWithoutDeleted();
        Assert.assertNotNull(listOut);
        Assert.assertTrue(listOut.size() > 0);
        List listAll = dao.getAllWithoutDeleted();
        Assert.assertNotNull(listAll);
        Assert.assertTrue(listAll.size() >= listOut.size());
    }

    @Test
    public void testGetAllVisibleUserCustomPlace() throws Exception {
        List listOut = dao.getAllVisibleUserCustomPlace(USER_ID);
        Assert.assertNotNull(listOut);
        List listAll = dao.getAllVisibleUserCustomPlace(USER_ID);
        Assert.assertNotNull(listAll);
        Assert.assertTrue(listAll.size() >= listOut.size());
    }

    @Test
    public void testGetAllVisibleUserFavorPlace() throws Exception {
        List listOut = dao.getAllVisibleUserFavorPlace(USER_ID);
        Assert.assertNotNull(listOut);
        Assert.assertTrue(listOut.size() > 0);
        List listAll = dao.getAllVisibleUserFavorPlace(USER_ID);
        Assert.assertNotNull(listAll);
        Assert.assertTrue(listAll.size() >= listOut.size());
    }

    @Test
    public void testCreateAndReturnIndex() throws Exception {
        Place place = new Place(ID, LATITUDE, LONGITUDE, VISIBLE, CATEGORY_ID, RATING, PLACE_TIME, RECOMENDED, CUSTOM, DELETED, RECOM_TIME, IS_RECOMMENDED);
        Integer placeIndex = dao.createAndReturnIndex(place);
        Assert.assertNotNull(placeIndex);
        Assert.assertTrue(placeIndex > 0);
    }

    @Test
    public void testGetAllPlaceRecomended() throws Exception {
        List<Place> placeRecomOut = dao.getAllPlaceRecomended();
        Assert.assertNotNull(placeRecomOut);
        Assert.assertTrue(placeRecomOut.size() > 0);
        List<Place> placeRecomAll = dao.getAllPlaceRecomended();
        Assert.assertNotNull(placeRecomAll);
        Assert.assertTrue(placeRecomAll.size() >= placeRecomOut.size());
    }

    @Test
    public void testGetAllPlaceVisible() throws Exception {
        List<Place> placeVisibleOut = dao.getAllPlaceVisible();
        Assert.assertNotNull(placeVisibleOut);
        Assert.assertTrue(placeVisibleOut.size() > 0);
        List<Place> placeVisibleAll = dao.getAllPlaceVisible();
        Assert.assertNotNull(placeVisibleAll);
        Assert.assertTrue(placeVisibleAll.size() >= placeVisibleOut.size());
    }

    @Test
    public void testGetPlaceByCategory() throws Exception {
        List<Place> placeCategoryOut = dao.getAllPlaceVisible();
        Assert.assertNotNull(placeCategoryOut);
        Assert.assertTrue(placeCategoryOut.size() > 0);
        List<Place> placeCategoryAll = dao.getAllPlaceVisible();
        Assert.assertNotNull(placeCategoryAll);
        Assert.assertTrue(placeCategoryAll.size() >= placeCategoryOut.size());
    }

    @Test
    public void testGetPlaceByCategoryRecomended() throws Exception {
        List<Place> placeCategoryRecOut = dao.getAllPlaceVisible();
        Assert.assertNotNull(placeCategoryRecOut);
        Assert.assertTrue(placeCategoryRecOut.size() > 0);
        List<Place> placeCategoryRecAll = dao.getAllPlaceVisible();
        Assert.assertNotNull(placeCategoryRecAll);
        Assert.assertTrue(placeCategoryRecAll.size() >= placeCategoryRecOut.size());
    }

    @Test
    public void testGetPlaceByUserId() throws Exception {
        List<Place> placeByIdOut = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(placeByIdOut);
        Assert.assertTrue(placeByIdOut.size() > 0);
        List<Place> placeByIdAll = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(placeByIdAll);
        Assert.assertTrue(placeByIdAll.size() >= placeByIdOut.size());
    }

    @Test
    public void testGetPlaceByWayId() throws Exception {
        List<Place> placeByIdOut = dao.getPlaceByWayId(WAY_ID);
        Assert.assertNotNull(placeByIdOut);
        Assert.assertTrue(placeByIdOut.size() > 0);
        List<Place> placeByIdAll = dao.getPlaceByWayId(WAY_ID);
        Assert.assertNotNull(placeByIdAll);
        Assert.assertTrue(placeByIdAll.size() >= placeByIdOut.size());
    }

    @Test
    public void testGetPlaceByWayIdDayNumber() throws Exception {
        List<Place> placeByIdAndDayOut = dao.getPlaceByWayIdDayNumber(WAY_ID, DAY_NUM);
        Assert.assertNotNull(placeByIdAndDayOut);
        Assert.assertTrue(placeByIdAndDayOut.size() > 0);
        List<Place> placeByIdAndDayAll = dao.getPlaceByWayIdDayNumber(WAY_ID, DAY_NUM);
        Assert.assertNotNull(placeByIdAndDayAll);
        Assert.assertTrue(placeByIdAndDayAll.size() >= placeByIdAndDayOut.size());
    }

    @Test(expected = PersistException.class)
    public void deletePlaceByUserIdPlaceId() throws Exception {
        dao.deletePlaceByUserIdPlaceId(USER_ID, ID);
        Assert.assertNotNull(dao.getPlaceByUserIdPlaceId(USER_ID, ID));
    }

    @Test
    public void testGetPlaceByLongitudeLatitude() throws Exception {
        Place placeByLongitudeLatitudeOut = dao.getPlaceByLongitudeLatitude(LONGITUDE, LATITUDE);
        Assert.assertNotNull(placeByLongitudeLatitudeOut);
    }

    @Test
    public void testGetPlaceByLongitudeLatitudeCon() throws Exception {
        Place placeByLongitudeLatitudeOut = dao.getPlaceByLongitudeLatitude(connection, LONGITUDE, LATITUDE);
        Assert.assertNotNull(placeByLongitudeLatitudeOut);
    }

    @Test
    public void testCreatePlaceWay() throws Exception {
        dao.createPlaceWay(ID, WAY_ID, DAY_NUM, TIME);
        List<Place> placeByWayIdOut = dao.getPlaceByWayId(WAY_ID);
        Assert.assertNotNull(placeByWayIdOut);
        Assert.assertTrue(placeByWayIdOut.size() > 0);
        List<Place> placeByWayIdAll = dao.getPlaceByWayId(WAY_ID);
        Assert.assertNotNull(placeByWayIdAll);
        Assert.assertTrue(placeByWayIdAll.size() >= placeByWayIdOut.size());
    }

    @Test
    public void testCreatePlaceWayCon() throws Exception {
        dao.createPlaceWay(connection, ID, WAY_ID, DAY_NUM, TIME);
        List<Place> placeByWayIdOut = dao.getPlaceByWayId(WAY_ID);
        Assert.assertNotNull(placeByWayIdOut);
        Assert.assertTrue(placeByWayIdOut.size() > 0);
        List<Place> placeByWayIdAll = dao.getPlaceByWayId(WAY_ID);
        Assert.assertNotNull(placeByWayIdAll);
        Assert.assertTrue(placeByWayIdAll.size() >= placeByWayIdOut.size());
    }

    @Test
    public void testCreatePlaceUser() throws Exception {
        dao.createPlaceUser(ID, USER_ID);
        List<Place> placeUserOut = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(placeUserOut);
        Assert.assertTrue(placeUserOut.size() > 0);
        List<Place> placeUserAll = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(placeUserAll);
        Assert.assertTrue(placeUserAll.size() >= placeUserOut.size());
    }

    @Test
    public void testGetPlaceByUserIdPlaceId() throws Exception {
        Integer placeByUserIdPlaceIdOut = dao.getPlaceByUserIdPlaceId(ID, USER_ID);
        Assert.assertNotNull(placeByUserIdPlaceIdOut);
        Assert.assertTrue(placeByUserIdPlaceIdOut > 0);
    }

}