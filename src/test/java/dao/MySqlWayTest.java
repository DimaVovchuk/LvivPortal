package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlWayDao;
import com.lab.epam.entity.Way;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleguk on 07.07.2015.
 */
public class MySqlWayTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlWayDao dao;
    private static final Integer USER_ID = 1;
    private static final Integer ID = 2;
    private static final Integer RATING = 2;
    private static final String NAME = "GreatLviv";
    private static final Boolean VISIBLE = true;
    private static final Integer WAY_DAYS = 5;
    private static final Integer WAY_TIME = 40;
    private static final Date BEGIN = new Date(2015-06-23);
    private static final Date END = new Date(2015-06-24);
    private static final Boolean DELETED = false;
    private static final Boolean RECOMENDED = true;
    private static final Boolean IS_RECOMMEND = true;

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlWayDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testCreate() throws Exception {
        List list = dao.getAll();
        Way way = new Way(ID, RATING, NAME, VISIBLE, WAY_DAYS, WAY_TIME, BEGIN, END, DELETED, RECOMENDED, IS_RECOMMEND);
        way.setId(list.size()+50);
        dao.create(way);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(way);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
    }

    @Test public void testGetByPK() throws Exception
    {
        Way way = dao.getByPK(ID);
        Assert.assertNotNull(way);
    }

    @Test
    public void testDelete() throws Exception {
        List list = dao.getAll();
        Way way = new Way(ID, RATING, NAME, VISIBLE, WAY_DAYS, WAY_TIME, BEGIN, END, DELETED, RECOMENDED, IS_RECOMMEND);
        way.setId(list.size()+50);
        dao.create(way);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(way);
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
     public void testGetWaysByUserId() throws Exception {
        List listOut = dao.getWaysByUserId(USER_ID);
        Assert.assertNotNull(listOut);
        Assert.assertTrue(listOut.size() > 0);
        List listAll = dao.getAllWithoutDeleted();
        Assert.assertNotNull(listAll);
        Assert.assertTrue(listAll.size() >= listOut.size());
    }

    @Test
    public void testGetWaysByWayId() throws Exception {
        List listOut = dao.getWaysByWayId(ID);
        Assert.assertNotNull(listOut);
        Assert.assertTrue(listOut.size() > 0);
        List listAll = dao.getAllWithoutDeleted();
        Assert.assertNotNull(listAll);
        Assert.assertTrue(listAll.size() >= listOut.size());
    }

    @Test
    public void testCreateWithCon() throws Exception {
        Way way = new Way(dao.getAll().size()+50, RATING, NAME, VISIBLE, WAY_DAYS, WAY_TIME, BEGIN, END, DELETED, RECOMENDED, IS_RECOMMEND);
        dao.create(connection, way);
        Way wayOut = dao.getLastAdded();
        Assert.assertNotNull(wayOut);
    }

    @Test
    public void testGetLastAdded() throws Exception {
        Way way = dao.getLastAdded();
        Assert.assertNotNull(way);
    }

    @Test
    public void testGetLastAddedWithCon() throws Exception {
        Way way = dao.getLastAdded(connection);
        Assert.assertNotNull(way);
    }

    @Test
    public void testCreateUserWay() throws Exception {
        dao.createUserWay(USER_ID, ID, WAY_DAYS);
        Way wayOut = dao.getLastAdded();
        Assert.assertNotNull(wayOut);
    }

    @Test
    public void testCreateUserWayWithCon() throws Exception {
        dao.createUserWay(connection, USER_ID, ID, WAY_DAYS);
        Way wayOut = dao.getLastAdded();
        Assert.assertNotNull(wayOut);
    }

    @Test(expected = PersistException.class)
    public void testUpdateWayDay() throws Exception {
        dao.updateWayDay(USER_ID, ID, WAY_DAYS);
        List<Way> wayList = dao.getWaysByUserId(USER_ID);
        Assert.assertNotNull(wayList);
        Assert.assertTrue(wayList.size() > 0);
    }

    @Test
    public void testUpdateWayBeginDate() throws Exception {
        dao.updateWayBeginDate(ID, BEGIN);
        List<Way> wayList = dao.getWaysByWayId(ID);
        Assert.assertNotNull(wayList);
        Assert.assertTrue(wayList.size() > 0);
    }

    @Test
    public void testUpdateWayEndDate() throws Exception {
        dao.updateWayEndDate(ID, END);
        List<Way> wayList = dao.getWaysByWayId(ID);
        Assert.assertNotNull(wayList);
        Assert.assertTrue(wayList.size() > 0);
    }

    @Test
    public void testUpdateWayRating() throws Exception {
        dao.updateWayRating(ID, RATING);
        List<Way> wayList = dao.getWaysByWayId(ID);
        Assert.assertNotNull(wayList);
        Assert.assertTrue(wayList.size() > 0);
    }

    @Test
    public void testGetAllWayRecomended() throws Exception {
        List listOut = dao.getAllWayRecomended();
        Assert.assertNotNull(listOut);
        Assert.assertTrue(listOut.size() > 0);
        List listAll = dao.getAllWayRecomended();
        Assert.assertNotNull(listAll);
        Assert.assertTrue(listAll.size() >= listOut.size());
    }

}
