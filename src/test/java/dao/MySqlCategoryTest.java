package dao;

import com.lab.epam.dao.Identified;
import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlCategoryDao;
import com.lab.epam.entity.Category;
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
public class MySqlCategoryTest {

    private Connection connection;
    ConnectionPool connectionPool;
    private MySqlCategoryDao dao;
    private static final String CATEGORY = "Architectural sights";
    private static final Integer ID = 1;

    @Before
    public void setUp() throws PersistException, SQLException {
        connectionPool = ConnectionManager.getConnection();
        connection = connectionPool.retrieve();
        connection.setAutoCommit(false);
        dao = new MySqlCategoryDao();
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connectionPool.putback(connection);
    }

    @Test
    public void testCreate() throws Exception {
        List list = dao.getAll();
        Category cat = new Category("ololol");
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
        Category category = dao.getByPK(ID);
        Assert.assertNotNull(category);
    }

    @Test
    public void testDelete() throws Exception {
        List list = dao.getAll();
        Category cat = new Category("ololol");
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
    public void testGetCategoryByName() throws Exception {
        Category category = dao.getCategoryByName(CATEGORY);
        Assert.assertNotNull(category);
    }

}
