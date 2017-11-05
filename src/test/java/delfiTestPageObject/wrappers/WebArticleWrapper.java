package delfiTestPageObject.wrappers;

import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.webPages.WebArticlePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebArticleWrapper {

    private final BaseFunctions baseFunctions;
    private final WebElement element;
    private NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By TITLE = By.xpath(".//a[contains(@class,'title')]");
    private static final By COMMENT_COUNT = By.xpath(".//a[contains(@class,'comment-count')]");
    private static final Logger LOGGER = LogManager.getLogger(WebArticleWrapper.class);

    public WebArticleWrapper(BaseFunctions bs, WebElement element) {
        this.baseFunctions = bs;
        this.element = element;

        LOGGER.info("Checking for the presence of key elements on web article wrapper");

        LOGGER.info("Checking for the presence of article title");
        Assert.assertTrue("No article title!", baseFunctions.isPresentElement(TITLE));
    }

    public String getArticleTitle() {
        LOGGER.info("Getting article title");
        return element.findElement(TITLE).getText();
    }

    /*public Long getID() {
        LOGGER.info("Getting article ID");
        return numberFromTextHelper.getLongFromString(element.findElement(TITLE).getAttribute("href"));
    }*/

    public Integer getCommentCount() {
        LOGGER.info("Getting article comment count");
        return element.findElements(COMMENT_COUNT).isEmpty() ? 0 : numberFromTextHelper.getIntegerFromString(element.findElement(COMMENT_COUNT).getText());
    }

    public WebArticlePage openArticlePage() {
        LOGGER.info("Opening article page by clicking on title");
        element.findElement(TITLE).click();
        return new WebArticlePage(baseFunctions);
    }

    /*public void clickOnTitle() {
        LOGGER.info("Clicking on title");
        element.findElement(TITLE).click();
    }*/
}
