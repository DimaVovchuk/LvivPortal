package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserRatingDao;
import com.lab.epam.entity.UserRating;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Oleguk on 10.07.2015.
 */
public class MySqlUserRatingTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlUserRatingDao dao;
    private static final Integer USER_ID = 5;
    private static final Integer COMPANY_ID = 5;


    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlUserRatingDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetClassModel() throws Exception {
        Assert.assertNotNull(new MySqlUserRatingDao().getClassModel());
    }

    @Test
    public void testGetUserImageByUserId() throws Exception {
        UserRating userRating = dao.getUseRatingByCompanyAndUser(USER_ID, COMPANY_ID);
        Assert.assertNotNull(userRating);
    }
}
