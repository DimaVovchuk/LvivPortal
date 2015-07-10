package workWithMap;

import com.google.api.client.util.Maps;
import com.lab.epam.dao.PersistException;
import com.lab.epam.workWithMap.Distance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Oleguk on 09.07.2015.
 */
public class DistanceTest {
    private static final String ORIGIN = "49.842189 24.029202";
    private static final String DESTINITION = "49.833968 24.026788";
    Distance distance;

    @Before
    public void setUp() throws PersistException, SQLException {
        distance = new Distance();
    }

    @Test
    public void testEncodeParams() throws Exception {
        Map<String,Double> distAndTime = distance.getDistanceAndTime(ORIGIN, DESTINITION);
        Assert.assertNotNull(distAndTime);
        Assert.assertTrue(distAndTime.size() > 0);
    }
}
