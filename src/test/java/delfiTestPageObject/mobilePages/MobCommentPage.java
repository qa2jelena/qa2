package delfiTestPageObject.mobilePages;

import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.pages.BaseFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class MobCommentPage {

    BaseFunctions baseFunctions;
    NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By MENU_BTN = By.xpath("//a[@class='menu-open']");
    private static final By TITLE = By.xpath(".//h1//a//span[contains(@class,'text')]");
    private static final By REG_COMMENT_COUNT = By.xpath("//*[@id='comments-listing']//a[contains(text(),'Зарегистрированные ')]/span");
    private static final By ANON_COMMENT_COUNT = By.xpath("//*[@id='comments-listing']//a[contains(text(),'Анонимные ')]/span");
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
        String titleToParse = baseFunctions.getElement(TITLE).getText();
        if (titleToParse.contains(": комментарии 1-")) {
            titleToParse = titleToParse.substring(0, titleToParse.indexOf(": комментарии 1-"));
        }
        return titleToParse;
    }

    public Integer getRegisteredCommentCount() {
        LOGGER.info("Getting article registered comment count");
        if (baseFunctions.isPresentElement(REG_COMMENT_COUNT)) {
            String countToParse = baseFunctions.getElement(REG_COMMENT_COUNT).getText();
            return numberFromTextHelper.getIntegerFromString(countToParse);
        }
        return 0;
    }

    public Integer getAnonymousCommentCount() {
        LOGGER.info("Getting article anonymous comment count");
        if (baseFunctions.isPresentElement(ANON_COMMENT_COUNT)) {
            String countToParse = baseFunctions.getElement(ANON_COMMENT_COUNT).getText();
            return numberFromTextHelper.getIntegerFromString(countToParse);
        }
        return 0;
    }
}
