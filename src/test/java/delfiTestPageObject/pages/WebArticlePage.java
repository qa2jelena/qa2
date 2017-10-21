package delfiTestPageObject.pages;

import delfiTestPageObject.helpers.NumberFromTextHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class WebArticlePage {

    BaseFunctions baseFunctions;
    NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By TITLE = By.xpath(".//h1/span");
    private static final By COMMENT_COUNT = By.xpath("//*[@id='article']//a[contains(@class,'comment-count')]");
    private static final By READ_COMMENTS_BTN = By.xpath("//*[@id='comment-dark-skin-wrapper']//a[contains(@class,'comment-add-form-listing-url comment-add-form-listing-url-registered')]");
    private static final Logger LOGGER = LogManager.getLogger(WebArticlePage.class);

    public WebArticlePage(BaseFunctions bs) {
        this.baseFunctions = bs;

        LOGGER.info("Checking for the presence of key elements on web article page");

        LOGGER.info("Checking for the presence of title");
        Assert.assertTrue("No title on web article page!", baseFunctions.isPresentElement(TITLE));
    }

    public void waitTitlePresence(int timeOutInSeconds) {
        LOGGER.info("Waiting for title presence");
        baseFunctions.waitForElement(TITLE, timeOutInSeconds);
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunctions.getElement(TITLE).getText();
    }

    public Integer getCommentCount() {
        LOGGER.info("Getting article comment count");
        if (baseFunctions.isPresentElement(COMMENT_COUNT)) {
            String countToParse = baseFunctions.getElement(COMMENT_COUNT).getText();
            return numberFromTextHelper.getIntegerFromString(countToParse);
        }
        return 0;
    }

    public boolean isReadCommentButtonPresent() {
        LOGGER.info("Checking article for read comment button presence");
        return baseFunctions.isPresentElement(READ_COMMENTS_BTN);
    }

    public WebCommentPage openCommentPage() {
        LOGGER.info("Click article read comment button");
        baseFunctions.clickElement(READ_COMMENTS_BTN);
        return new WebCommentPage(baseFunctions);
    }

}
