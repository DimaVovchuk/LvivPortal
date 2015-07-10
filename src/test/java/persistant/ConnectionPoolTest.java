package persistant;

import com.lab.epam.dao.PersistException;
import com.lab.epam.persistant.ConnectionPool;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    private Connection connection;
    ConnectionPool connectionPool;

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = new ConnectionPool(10);
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetConnection() throws Exception {
        Assert.assertNotNull(connectionPool.getConnection());
    }

    @Test
    public void testRetrive() throws Exception {
        Assert.assertNotNull(connectionPool.retrieve());
    }
}
