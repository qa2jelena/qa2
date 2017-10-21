package pageObjectFirstTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pageObjectFirstTest.pages.ArticlePage;
import pageObjectFirstTest.pages.BaseFunctions;
import pageObjectFirstTest.pages.HomePage;

public class PageObjectTest {

    BaseFunctions baseFunctions = new BaseFunctions(); // IMPORTANT!
    private static final Logger LOGGER = LogManager.getLogger(PageObjectTest.class);
    private static final String HOME_PAGE_URL = "http://rus.delfi.lv";


    @Test
    void delfiTest() {
        LOGGER.info("Open homepage");
        baseFunctions.goToURL(HOME_PAGE_URL);

        LOGGER.info("Getting first article title");
        HomePage homePage = new HomePage(baseFunctions);
        WebElement article = homePage.getFirstArticle();
        String title = homePage.getTitle(article);

        LOGGER.info("Getting first article comment count");
        int commentCount = homePage.getTCommentCount(article);

        LOGGER.info("Open first article");
        ArticlePage articlePage = homePage.openArticle();

        LOGGER.info("Getting article title");

        LOGGER.info("Getting article comment count");
        LOGGER.info("Checking title");
        LOGGER.info("Checking comment");

        LOGGER.info("Open comment page");
        LOGGER.info("Getting title on comment page");
        LOGGER.info("Getting registered comment count");
        LOGGER.info("Getting anonim comment count");
        LOGGER.info("Getting sum of registered and anonim comment count");
        LOGGER.info("Checking title");
        LOGGER.info("Checking comment");

        LOGGER.info("Test is successful");
    }
}
