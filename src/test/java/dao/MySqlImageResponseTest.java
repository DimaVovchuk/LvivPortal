package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlImageResponseDao;
import com.lab.epam.dao.imp.MySqlWayRatingDao;
import com.lab.epam.entity.ImageResponse;
import com.lab.epam.entity.WayRating;
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
 * Created by Oleguk on 10.07.2015.
 */
public class MySqlImageResponseTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlImageResponseDao dao;
    private static final Integer  IMAGE_ID = 2;


    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlImageResponseDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetClassModel() throws Exception {
        Assert.assertNotNull(new MySqlImageResponseDao().getClassModel());
    }

    @Test
    public void testGetWayRatingByWayAndUser() throws Exception {
        List<ImageResponse> wayRating = dao.getImageResponseByImage(IMAGE_ID);
        Assert.assertNotNull(wayRating);
        Assert.assertTrue(wayRating.size() > 0);
    }
}
