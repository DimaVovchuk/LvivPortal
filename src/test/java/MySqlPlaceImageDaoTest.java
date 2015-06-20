import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlPlaceDao;
import com.lab.epam.dao.imp.MySqlPlaceImageDao;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceImage;
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
 * Created by Admin on 20.06.2015.
 */
public class MySqlPlaceImageDaoTest {

    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlPlaceImageDao dao;
    private static final Integer ID = 1;
    private static final Integer  PLACE_ID = 1;
    private static final String  REFERENCE = "new.jpg";

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlPlaceImageDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testCreate() throws Exception {
        List list = dao.getAll();
        PlaceImage cat = new PlaceImage(PLACE_ID, REFERENCE);
        cat.setId(list.size()+1);
        dao.create(cat);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(cat);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);

    }

    @Test public void testGetByPK() throws Exception
    {
        PlaceImage category = dao.getByPK(ID);
        Assert.assertNotNull(category);
    }

    @Test
    public void testDelete() throws Exception {
        List list = dao.getAll();
        PlaceImage cat = new PlaceImage(PLACE_ID, REFERENCE);
        cat.setId(list.size()+1);
        dao.create(cat);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao.delete(cat);
        list = dao.getAllWithoutDeleted();
        Assert.assertNotNull(list);
        int newSize = list.size();
        Assert.assertEquals(1, oldSize - newSize);
    }

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
    public void testGetPlaceImageByPlaceId() throws Exception {
        PlaceImage list = dao.getPlaceImageByPlaceId(PLACE_ID);
        Assert.assertNotNull(list);
    }

}
