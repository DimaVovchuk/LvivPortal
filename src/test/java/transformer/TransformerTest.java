package transformer;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.entity.User;
import com.lab.epam.persistant.ConnectionManager;
import com.lab.epam.persistant.ConnectionPool;
import com.lab.epam.transformer.Transformer;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleguk on 09.07.2015.
 */
public class TransformerTest {

    /*private Connection connection;
    ConnectionPool connectionPool;
    Class<User> clazz;
    Transformer<User> transformer;
    private MySqlUserDao userDao;
    private static final String SQL = "SELECT * FROM user WHERE id = 5";

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        transformer = new Transformer(clazz);
        userDao = new MySqlUserDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetTableName() throws Exception {
        String tableName = transformer.getTableName();
        Assert.assertNotNull(tableName);
    }

    @Test
    public void testGetObjectColumns() throws Exception {
        User user = userDao.getByPK(5);
        Map<String, Object> columns = transformer.getObjectColumns(user);
        Assert.assertNotNull(columns);
        Assert.assertTrue(columns.size() > 0);
    }

    @Test
    public void testSetRowInDB() throws Exception {
        User user = userDao.getByPK(5);
        List<String> rows = transformer.setRowInDB(user);
        Assert.assertNotNull(rows);
        Assert.assertTrue(rows.size() > 0);
    }

    @Test
    public void testRowToObject() throws Exception {
        PreparedStatement statement = connection.prepareStatement(SQL);
        ResultSet rs = statement.executeQuery();
        List<User> rowsToObj = transformer.rowToObject(rs);
        Assert.assertNotNull(rowsToObj);
        Assert.assertTrue(rowsToObj.size() > 0);
    }*/
}
