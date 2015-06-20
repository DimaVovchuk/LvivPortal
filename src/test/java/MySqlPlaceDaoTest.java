import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.entity.Category;
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
 * Created by Admin on 19.06.2015.
 */
public class MySqlPlaceDaoTest {

    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlPlaceDao dao;
    private static final Integer ID = 1;
    private static final Integer CATEGORY_ID = 1;
    private static final Integer RATING = 0;
    private static final Boolean  VISIBLE = false;
    private static final Integer  TIME = 4;
    private static final Integer  USER_ID = 5;
    private static final Integer  WAY_ID = 1;
    private static final Integer  PLACE_ID = 1;
    private static final String  LONGITUDE = "24.029202";
    private static final String  LATITUDE = "49.842189";

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
        Place cat = new Place("ololol" + list.size()+1,"oololo","olololol",CATEGORY_ID,RATING, VISIBLE,TIME);
        cat.setId(list.size()+1);
        dao.create(cat);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(cat);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);

    }

    @Test public void testGetByPK() throws Exception
    {
        Place category = dao.getByPK(ID);
        Assert.assertNotNull(category);
    }

    @Test
    public void testDelete() throws Exception {
        List list = dao.getAll();
        Place cat = new Place("ololo" + list.size()+1,"oololo","olololol",CATEGORY_ID,RATING, VISIBLE,TIME);
        cat.setId(list.size()+1);
        dao.create(cat);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(cat);
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
    public void testGetPlaceByUserId() throws Exception {
        List list = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetPlaceByWayId() throws Exception {
        List list = dao.getPlaceByWayId(WAY_ID);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    public void testDeletePlaceByUserIdPlaceId() throws Exception {
        List list = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.deletePlaceByUserIdPlaceId(USER_ID, PLACE_ID);
        list = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(list);
        int newSize = list.size();
        Assert.assertEquals(1, oldSize - newSize);
    }

    @Test
    public void testGetPlaceByLongitudeLatitude() throws Exception {
        Place place = dao.getPlaceByLongitudeLatitude(LONGITUDE, LATITUDE);
        Assert.assertNotNull(place);
    }

    @Test
    public void testCreatePlaceWay() throws Exception {
        List list = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        list = dao.getPlaceByUserId(USER_ID);
        Assert.assertNotNull(list);
        int newSize = list.size();
        Assert.assertEquals(0, newSize - oldSize);
    }

}
