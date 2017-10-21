package delfiTestPageObject.mobilePages;

import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.pages.BaseFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class MobArticlePage {

    BaseFunctions baseFunctions;
    NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By MENU_BTN = By.xpath("//a[@class='menu-open']");
    private static final By TITLE = By.xpath(".//h1");
    private static final By COMMENT_COUNT = By.xpath("//*[@id='article']//a[contains(@class,'commentCount')]");
    private static final By READ_COMMENTS_LINK = By.xpath("//*[@id='comment-dark-skin-wrapper']//*[contains(@class,'comment-read-comments-link')]/a");
    private static final Logger LOGGER = LogManager.getLogger(MobArticlePage.class);

    public MobArticlePage(BaseFunctions bs) {
        this.baseFunctions = bs;

        LOGGER.info("Checking for the presence of key elements on mob article page");

        LOGGER.info("Checking for the presence of menu button");
        Assert.assertTrue("No menu button on mob article page!", baseFunctions.isDisplayedElement(MENU_BTN));

        LOGGER.info("Checking for the presence of title");
        Assert.assertTrue("No title on mob article page!", baseFunctions.isPresentElement(TITLE));
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

    public boolean isReadCommentLinkPresent() {
        LOGGER.info("Checking article for read comment link presence");
        return baseFunctions.isPresentElement(READ_COMMENTS_LINK);
    }

    public MobCommentPage openCommentPage() {
        LOGGER.info("Click article read comment link");
        baseFunctions.clickElement(READ_COMMENTS_LINK);
        return new MobCommentPage(baseFunctions);
    }

}
