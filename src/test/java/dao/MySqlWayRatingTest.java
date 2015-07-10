package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlWayRatingDao;
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
 * Created by Oleguk on 08.07.2015.
 */
public class MySqlWayRatingTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlWayRatingDao dao;
    private static final Integer RATING = 1;
    private static final Integer  USER_ID = 5;
    private static final Integer  WAY_ID = 43;


    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlWayRatingDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetClassModel() throws Exception {
        Assert.assertNotNull(new MySqlWayRatingDao().getClassModel());
    }

    @Test
    public void testGetWayRatingByWayAndUser() throws Exception {
        WayRating wayRating = new WayRating(USER_ID, WAY_ID, RATING);
        Assert.assertNotNull(wayRating);
    }


}
