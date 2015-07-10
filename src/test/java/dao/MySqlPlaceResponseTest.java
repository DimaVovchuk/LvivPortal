package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceResponseDao;
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
 * Created by Oleguk on 08.07.2015.
 */
public class MySqlPlaceResponseTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlPlaceResponseDao dao;
    private static final Integer ID = 1;
    private static final String LATITUDE = "78.89954";
    private static final String LONGITUDE = "61.023002";
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
        dao = new MySqlPlaceResponseDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testPlaceResponseByPlace() throws Exception {
        Place place = new Place(ID, LATITUDE, LONGITUDE, VISIBLE, CATEGORY_ID, RATING, PLACE_TIME, RECOMENDED, CUSTOM, DELETED, RECOM_TIME, IS_RECOMMENDED);
        List list = dao.getPlaceResponseByPlace(place.getId());
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetClassModel() throws Exception {
        Assert.assertNotNull(new MySqlPlaceResponseDao().getClassModel());
    }
}
