package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.dao.imp.MySqlUserImageDao;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
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
public class MySqlUserImageTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlUserImageDao dao;
    private MySqlUserDao userDao;
    private static final Integer USER_ID = 5;
    //private static final String LOGIN = "LeSS9";


    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlUserImageDao();
        userDao = new MySqlUserDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetClassModel() throws Exception {
        Assert.assertNotNull(new MySqlUserImageDao().getClassModel());
    }

    @Test
    public void testGetUserImageByUserId() throws Exception {
        //User user = userDao.getUserByLogin(LOGIN);
        List<UserImage> userImageList = dao.getUserImageByUserId(USER_ID);
        Assert.assertNotNull(userImageList);
        Assert.assertTrue(userImageList.size() > 0);
    }

    @Test
    public void testGetUserImageByUserIdOne() throws Exception {
        //User user = userDao.getUserByLogin(LOGIN);
        UserImage userImage = dao.getUserImageByUserIdOne(USER_ID);
        Assert.assertNotNull(userImage);
    }
}
