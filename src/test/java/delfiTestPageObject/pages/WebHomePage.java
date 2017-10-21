package delfiTestPageObject.pages;

import delfiTestPageObject.helpers.NumberFromTextHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebHomePage {

    BaseFunctions baseFunctions;
    NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By LOGO_HEADER = By.xpath("//a[@class='headerLogo']");
    private static final By TOP_CONTENT_HEADER = By.xpath("//div[@class='headerTopContent']");
    private static final By MAIN_NAVIGATION_HEADER = By.xpath("//nav[@class='headerMainNavigation headerSeparatedNav']");
    private static final By CHANNEL_CATEGORIES_HEADER = By.xpath("//nav[@class='headerSeparatedNav headerChannelCategories ']");
    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']");
    private static final By TITLE = By.xpath("a[contains(@class,'top2012-title')]");
    private static final By COMMENT_COUNT = By.xpath("a[contains(@class,'comment-count')]");
    private static final By FIRST_ARTICLE_TITLE = By.xpath(".//a[contains(@class,'top2012-title')]");
    private static final Logger LOGGER = LogManager.getLogger(WebHomePage.class);

    public WebHomePage(BaseFunctions bs) {
        this.baseFunctions = bs;

        LOGGER.info("Checking for the presence of key elements on web home page");

        LOGGER.info("Checking title");
        Assert.assertEquals("Incorrect title on web home page!", "DELFI - Ведущий новостной портал в Латвии - DELFI", baseFunctions.getPageTitle());

        LOGGER.info("Checking for the presence of logo");
        Assert.assertTrue("No logo on web home page!", baseFunctions.isDisplayedElement(LOGO_HEADER));

        LOGGER.info("Checking for the presence of top content header");
        Assert.assertTrue("No top content header on web home page!", baseFunctions.isDisplayedElement(TOP_CONTENT_HEADER));

        LOGGER.info("Checking for the presence of main navigation");
        Assert.assertTrue("No main navigation on web home page!", baseFunctions.isDisplayedElement(MAIN_NAVIGATION_HEADER));

        LOGGER.info("Checking for the presence of channel categories");
        Assert.assertTrue("No channel categories on web home page!", baseFunctions.isDisplayedElement(CHANNEL_CATEGORIES_HEADER));

        LOGGER.info("Checking for the presence of articles");
        Assert.assertTrue("No articles on web home page!", baseFunctions.isPresentElement(ARTICLE));
    }

    public List<WebElement> getArticleList() {
        LOGGER.info("Getting article list");
        return baseFunctions.getElements(ARTICLE);
    }

    public String getTitle(WebElement article) {
        LOGGER.info("Getting article title");
        return article.findElement(TITLE).getText();
    }

    public Long getID(WebElement article) {
        LOGGER.info("Getting article ID");
        String idToParse = article.findElement(TITLE).getAttribute("href");
        return numberFromTextHelper.getLongFromString(idToParse);
    }

    public Integer getCommentCount(WebElement article) {
        LOGGER.info("Getting article comment count");
        if (article.findElements(COMMENT_COUNT).size() != 0) {
            String countToParse = article.findElement(COMMENT_COUNT).getText();
            return numberFromTextHelper.getIntegerFromString(countToParse);
        }
        return 0;
    }

    public WebArticlePage openFirstArticlePage() {
        LOGGER.info("Click first article title");
        baseFunctions.clickElement(FIRST_ARTICLE_TITLE);
        return new WebArticlePage(baseFunctions);
    }

}
