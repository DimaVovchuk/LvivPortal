package helper;

import com.lab.epam.helper.ClassName;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Oleguk on 09.07.2015.
 */
public class ClassNameTest {

    @Test
    public void testGetCurrentClassName() throws Exception {
        String className = ClassName.getCurrentClassName();
        Assert.assertNotNull(className);
    }

}
