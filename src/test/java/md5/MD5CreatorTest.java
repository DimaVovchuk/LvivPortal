package md5;

import com.lab.epam.md5.MD5Creator;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Oleguk on 09.07.2015.
 */
public class MD5CreatorTest {

    @Test
    public void testGetMD5() throws Exception {
        String md5Password = MD5Creator.getMD5("qwerty12345");
        Assert.assertNotNull(md5Password);
        Assert.assertTrue(md5Password != "");
    }
}
