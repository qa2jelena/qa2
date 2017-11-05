package delfiTestPageObject.webPages;

import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.helpers.TrimTitleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class WebCommentPage {

    private BaseFunctions baseFunctions;
    private TrimTitleHelper trimTitleHelper = new TrimTitleHelper();
    private NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By TITLE = By.xpath(".//h1/a");
    private static final By REG_COMMENT_COUNT = By.xpath(".//*[@id='comments-listing']//a[contains(text(),'Зарегистрированные ')]/span");
    private static final By ANON_COMMENT_COUNT = By.xpath(".//*[@id='comments-listing']//a[contains(text(),'Анонимные ')]/span");
    private static final Logger LOGGER = LogManager.getLogger(WebCommentPage.class);

    public WebCommentPage(BaseFunctions bs) {
        this.baseFunctions = bs;

        LOGGER.info("Checking for the presence of key elements on web comment page");

        LOGGER.info("Checking for the presence of title");
        Assert.assertTrue("No title on web comment page!", baseFunctions.isPresentElement(TITLE));
    }

    public void waitTitlePresence(int timeOutInSeconds) {
        LOGGER.info("Waiting for title presence");
        baseFunctions.waitForElement(TITLE, timeOutInSeconds);
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return trimTitleHelper.trimTitle(baseFunctions.getElement(TITLE).getText());
    }

    public Integer getRegisteredCommentCount() {
        LOGGER.info("Getting article registered comment count");
        return getCommentCountFrom(REG_COMMENT_COUNT);
    }

    public Integer getAnonymousCommentCount() {
        LOGGER.info("Getting article anonymous comment count");
        return getCommentCountFrom(ANON_COMMENT_COUNT);
    }

    private Integer getCommentCountFrom(By locator) {
        LOGGER.info("Getting comment count");
        return numberFromTextHelper.getIntegerFromString(baseFunctions.getElement(locator).getText());
    }
}
