package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.dao.imp.MySqlPlaceRatingDao;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceRating;
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
 * Created by Admin on 20.06.2015.
 */
public class MySqlPlaceRatingTest {

    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlPlaceRatingDao dao;
    private static final Integer ID = 1;
    private static final Integer RATING = 0;
    private static final Integer  USER_ID = 5;
    private static final Integer  WAY_ID = 1;
    private static final Integer  PLACE_ID = 1;

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlPlaceRatingDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testCreate() throws Exception {
        List list = dao.getAll();
        PlaceRating placeRating = new PlaceRating(USER_ID, PLACE_ID, RATING);
        placeRating.setId(list.size()+1);
        dao.create(placeRating);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(placeRating);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);

    }

    @Test public void testGetByPK() throws Exception
    {
        PlaceRating category = dao.getByPK(ID);
        Assert.assertNotNull(category);
    }

    @Test
    public void testDelete() throws Exception {
        List list = dao.getAll();
        PlaceRating placeRating = new PlaceRating(USER_ID, PLACE_ID, RATING);
        placeRating.setId(list.size()+1);
        dao.create(placeRating);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(placeRating);
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
    public void testGetPlaceRatingByPlaceAndUser() throws Exception {
        PlaceRating placeRating = dao.getPlaceRatingByPlaceAndUser(PLACE_ID, USER_ID);
        Assert.assertNotNull(placeRating);
    }

}
