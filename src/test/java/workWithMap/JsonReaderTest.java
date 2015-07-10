package workWithMap;

import com.lab.epam.workWithMap.JsonReader;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Oleguk on 09.07.2015.
 */
public class JsonReaderTest {
    private static final String URL = "http://maps.googleapis.com/maps/api/directions/json?mode=walking&origin=49.842189+24.029202&destination=49.833968+24.026788&sensor=false&language=ua";

    @Test
    public void readTest() throws Exception {
        JSONObject json = JsonReader.read(URL);
        System.out.println(json);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.length() > 0);
    }
}
