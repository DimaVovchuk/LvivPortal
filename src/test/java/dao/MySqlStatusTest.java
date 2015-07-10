package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlStatusDao;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Oleguk on 08.07.2015.
 */
public class MySqlStatusTest {

    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlStatusDao dao;

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlStatusDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetClassModel() throws Exception {
        Assert.assertNotNull(new MySqlStatusDao().getClassModel());
    }
}
