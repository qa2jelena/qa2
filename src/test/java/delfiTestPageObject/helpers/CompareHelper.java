package delfiTestPageObject.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class CompareHelper {

    private static final Logger LOGGER = LogManager.getLogger(CompareHelper.class);

    public void compareTitles(String titleErrorMessage, String webTitle, String mobTitle) {
        LOGGER.info("Comparing web and mob title");
        if (webTitle.length() == mobTitle.length()) {
            Assert.assertEquals(titleErrorMessage + webTitle, webTitle, mobTitle);
        } else if (webTitle.length() > mobTitle.length()) {
            Assert.assertTrue(titleErrorMessage + webTitle, webTitle.matches(getTitleRegex(mobTitle)));
        } else {
            Assert.assertTrue(titleErrorMessage + webTitle, mobTitle.matches(getTitleRegex(webTitle)));
        }
    }

    private String getTitleRegex(String title) {
        LOGGER.info("Getting title regex");
        return "(.*)" + title + "(.*)";
    }
}
