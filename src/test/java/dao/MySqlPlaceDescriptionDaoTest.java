package dao;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDescriptionDao;
import com.lab.epam.entity.PlaceDescription;
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
public class MySqlPlaceDescriptionDaoTest {

    private Connection connection;
    private MySqlPlaceDescriptionDao dao;
    ConnectionPool connectionPool;
    private static final Integer ID = 1;
    private static final Integer PLACE_ID = 1;
    private static final String  NAME = "name";
    private static final String  DESCRIPTION = "description";
    private static final String  LOCALE = "en";

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlPlaceDescriptionDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

//    @Test
//    public void testCreate() throws Exception {
//        List list = dao.getAll();
//        PlaceDescription cat = new PlaceDescription.Builder(PLACE_ID, LOCALE, NAME, DESCRIPTION).build();
//        cat.setId(list.size()+4);
//        dao.create(cat);
//        list = dao.getAllWithoutDeleted();
//        Assert.assertNotNull(list);
//        int oldSize = list.size();
//        Assert.assertTrue(oldSize > 0);
//        dao.delete(cat);
//        list = dao.getAllWithoutDeleted();
//        Assert.assertNotNull(list);
//
//    }
//
//    @Test public void testGetByPK() throws Exception
//    {
//        PlaceDescription category = dao.getByPK(ID);
//        Assert.assertNotNull(category);
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        List list = dao.getAll();
//        PlaceDescription cat = new PlaceDescription.Builder(PLACE_ID, LOCALE, NAME, DESCRIPTION).build();
//        cat.setId(list.size()+4);
//        dao.create(cat);
//        list = dao.getAllWithoutDeleted();
//        Assert.assertNotNull(list);
//        int oldSize = list.size();
//        Assert.assertTrue(oldSize > 0);
//        dao.delete(cat);
//        list = dao.getAllWithoutDeleted();
//        Assert.assertNotNull(list);
//        int newSize = list.size();
//        Assert.assertEquals(1, oldSize - newSize);
//    }

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
    public void testGetPlaceDescriptionByIdPlace() throws Exception {
        PlaceDescription list = dao.getPlaceDescriptionByIdPlace(PLACE_ID, LOCALE);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.getId() == 2);
    }

}
