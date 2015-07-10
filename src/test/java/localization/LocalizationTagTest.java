package localization;

import com.lab.epam.dao.PersistException;
import com.lab.epam.localization.LocalizationTag;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;


public class LocalizationTagTest {
    LocalizationTag locTag;

    @Before
    public void setUp() throws PersistException, SQLException {
        locTag = new LocalizationTag();
    }

    /*@Test
    public void testDoStartTag() throws Exception {
        //HttpSession.class.newInstance().setAttribute("bundle");
        //HttpSession session = pageContext.getSession();
        Integer startTag = locTag.doStartTag();
        Assert.assertNotNull(startTag);
        Assert.assertTrue(startTag > 0);
    }*/

    @Test
    public void testDoEndTag() throws Exception {
        Integer endTag = locTag.doEndTag();
        Assert.assertNotNull(endTag);
        Assert.assertTrue(endTag > 0);
    }
}