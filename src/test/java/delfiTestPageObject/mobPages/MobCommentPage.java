package delfiTestPageObject.mobPages;

import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.helpers.TrimTitleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class MobCommentPage {

    private BaseFunctions baseFunctions;
    private TrimTitleHelper trimTitleHelper = new TrimTitleHelper();
    private NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By MENU_BTN = By.xpath(".//a[@class='menu-open']");
    private static final By TITLE = By.xpath(".//h1//a//span[contains(@class,'text')]");
    private static final By REG_COMMENT_COUNT = By.xpath(".//*[@id='comments-listing']//a[contains(text(),'Зарегистрированные ')]/span");
    private static final By ANON_COMMENT_COUNT = By.xpath(".//*[@id='comments-listing']//a[contains(text(),'Анонимные ')]/span");
    private static final Logger LOGGER = LogManager.getLogger(MobCommentPage.class);

    public MobCommentPage(BaseFunctions bs) {
        this.baseFunctions = bs;

        LOGGER.info("Checking for the presence of key elements on mob comment page");

        LOGGER.info("Checking for the presence of menu button");
        Assert.assertTrue("No menu button on mob comment page!", baseFunctions.isDisplayedElement(MENU_BTN));

        LOGGER.info("Checking for the presence of title");
        Assert.assertTrue("No title on mob comment page!", baseFunctions.isPresentElement(TITLE));
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
