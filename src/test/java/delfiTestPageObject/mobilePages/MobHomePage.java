package delfiTestPageObject.mobilePages;

import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.pages.BaseFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MobHomePage {

    BaseFunctions baseFunctions;
    NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By MENU_BTN = By.xpath("//a[@class='menu-open']");
    private static final By LOGO_HEADER = By.xpath("//a[@class='header-logo']");
    private static final By TOP_CONTENT_HEADER = By.xpath("//div[@class='md-block-title ']");
    private static final By ARTICLE = By.className("md-mosaic-title");
    private static final By TITLE = By.xpath("a[contains(@class,'md-scrollpos')]");
    private static final By COMMENT_COUNT = By.xpath("a[contains(@class,'commentCount')]");
    private static final By FIRST_ARTICLE_TITLE = By.xpath(".//a[contains(@class,'md-scrollpos')]");
    private static final Logger LOGGER = LogManager.getLogger(MobHomePage.class);

    public MobHomePage(BaseFunctions bs) {
        this.baseFunctions = bs;

        LOGGER.info("Checking for the presence of key elements on mob home page");

        LOGGER.info("Checking title");
        Assert.assertEquals("Incorrect title on mob home page!", "RUS DELFI", baseFunctions.getPageTitle());

        LOGGER.info("Checking for the presence of menu button");
        Assert.assertTrue("No menu button on mob home page!", baseFunctions.isDisplayedElement(MENU_BTN));

        LOGGER.info("Checking for the presence of logo");
        Assert.assertTrue("No logo on mob home page!", baseFunctions.isDisplayedElement(LOGO_HEADER));

        LOGGER.info("Checking for the presence of top content header");
        Assert.assertTrue("No top content header on mob home page!", baseFunctions.isDisplayedElement(TOP_CONTENT_HEADER));

        LOGGER.info("Checking for the presence of articles");
        Assert.assertTrue("No articles on mob home page!", baseFunctions.isPresentElement(ARTICLE));
    }

    public List<WebElement> getArticleList() {
        LOGGER.info("Getting article list");
        return baseFunctions.getElements(ARTICLE);
    }

    public String getTitle(WebElement article) {
        LOGGER.info("Getting article title");
        return article.findElement(TITLE).getText();
    }

    public Integer getCommentCount(WebElement article) {
        LOGGER.info("Getting article comment count");
        if (article.findElements(COMMENT_COUNT).size() != 0) {
            String countToParse = article.findElement(COMMENT_COUNT).getText();
            return numberFromTextHelper.getIntegerFromString(countToParse);
        }
        return 0;
    }

    public MobArticlePage openFirstArticlePage() {
        LOGGER.info("Click first article title");
        baseFunctions.clickElement(FIRST_ARTICLE_TITLE);
        return new MobArticlePage(baseFunctions);
    }

}
