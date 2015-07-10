package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.entity.User;
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
public class MySqlUserTest {
    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlUserDao dao;
    private static final Integer ID = 2;
    private static final Integer RATING = 2;
    private static final String NAME = "Usja";
    private static final String SURNAME = "Sanivna";
    private static final String LOGIN = "user";
    private static final String MAIL = "usja@gmail.com";
    private static final String PASSWORD = "1c8cea42766ee6bd9a43846163cd74d9";
    private static final String PHONE = "0671235462";
    private static final Integer STATUS = 1;
    private static final Integer ROLE_ID = 2;
    private static final String ABOUT = "I am coll user of cool webSite!";
    private static final Integer AVATAR = 1;
    private static final Boolean DELETED = false;
    private static final String COMPANYNAME = "";
    private static final String VK_ID = "107268680";


    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlUserDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testGetClassModel() throws Exception {
        Assert.assertNotNull(new MySqlUserDao().getClassModel());
    }

    @Test
    public void testGetQuantityOfAllUsers() throws Exception {
        Integer userQuantity =  dao.getQuantityOfAllUsers();
        Assert.assertNotNull(userQuantity);
        Assert.assertTrue(userQuantity > 0);
    }

    @Test
    public void testGetQuantityUsersByRoleId() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        Integer userQuantity =  dao.getQuantityUsersByRoleId(user.getRoleID());
        Assert.assertNotNull(userQuantity);
        Assert.assertTrue(userQuantity > 0);
    }

    @Test
    public void testGetQuantityUsersByStatusId() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        user.setStatus(STATUS);
        Integer userQuantity =  dao.getQuantityUsersByStatusId(user.getStatus());
        Assert.assertNotNull(userQuantity);
        Assert.assertTrue(userQuantity > 0);
    }

    @Test
    public void testGetUserByRole() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        List<User> userQuantity =  dao.getUserByRole(user.getRoleID());
        Assert.assertNotNull(userQuantity);
        Assert.assertTrue(userQuantity.size() > 0);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        User userQuantity =  dao.getUserByLogin(user.getLogin());
        Assert.assertNotNull(userQuantity);
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        User userQuantity =  dao.getUserByLogin(user.getMail());
        Assert.assertNotNull(userQuantity);
    }

    @Test
    public void testGetUserByVkIdInt() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        user.setVkId(VK_ID);
        User userQuantity =  dao.getUserByVkId(user.getVkId());
        Assert.assertNotNull(userQuantity);
    }

    @Test
    public void testGetUserByVkIdStr() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        user.setAbout(ABOUT);
        user.setRating(RATING);
        user.setStatus(STATUS);
        user.setAvatar(AVATAR);
        user.setVkId(VK_ID);
        User userQuantity =  dao.getUserByVkId(user.getVkId().toString());
        Assert.assertNotNull(userQuantity);
    }

    @Test
    public void testGetUserByPhone() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        User userQuantity =  dao.getUserByPhone(user.getPhone());
        Assert.assertNotNull(userQuantity);
    }

    @Test
    public void testCheckEmail() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        boolean checkEmail =  dao.checkEmail(user.getMail());
        Assert.assertTrue(checkEmail);
    }

    @Test
    public void testCheckPhone() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        boolean checkPhone =  dao.checkPhone(user.getPhone());
        Assert.assertTrue(checkPhone);
    }

    /*@Test
    public void testCheckLogin() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        boolean checkLogin =  dao.checkLogin(user.getLogin());
        Assert.assertTrue(checkLogin);
    }*/

    @Test
    public void testGetRoleID() throws Exception {
        User user = new User(new User.Builder(NAME, SURNAME, LOGIN, MAIL, PASSWORD, PHONE, ROLE_ID));
        Integer roleId =  dao.getRoleID(user.getLogin());
        Assert.assertNotNull(roleId);
        Assert.assertTrue(roleId > 0);
    }


}
