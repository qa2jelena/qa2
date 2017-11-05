package delfiTestPageObject.mobPages;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.wrappers.MobArticleWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MobHomePage {

    private BaseFunctions baseFunctions;
    private NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By MENU_BTN = By.xpath(".//a[@class='menu-open']");
    private static final By LOGO_HEADER = By.xpath(".//a[@class='header-logo']");
    private static final By TOP_CONTENT_HEADER = By.xpath(".//div[@class='md-block-title ']");
    private static final By ARTICLE = By.className("md-mosaic-title");
    private static final By TITLE = By.xpath(".//a[contains(@class,'md-scrollpos')]");
    private static final By COMMENT_COUNT = By.xpath(".//a[contains(@class,'commentCount')]");
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
        List<WebElement> articles = baseFunctions.getElements(ARTICLE);

        LOGGER.info("Found " + articles.size() + " articles on mob home page");
        return articles;
    }

    public String getTitle(WebElement article) {
        LOGGER.info("Getting article title");
        return article.findElement(TITLE).getText();
    }

    public Integer getCommentCount(WebElement article) {
        LOGGER.info("Getting article comment count");
        return article.findElements(COMMENT_COUNT).isEmpty() ? 0 : numberFromTextHelper.getIntegerFromString(article.findElement(COMMENT_COUNT).getText());
    }

    public MobArticlePage openFirstArticlePage() {
        LOGGER.info("Click first article title");
        baseFunctions.clickElement(TITLE);
        return new MobArticlePage(baseFunctions);
    }

    // FOR WRAPPERS:

    private List<MobArticleWrapper> getAllArticles() {
        LOGGER.info("Getting article list");
        List<WebElement> articles = getArticleList();

        LOGGER.info("Converting each article from article list to article wrapper, and adding it to article wrapper list");
        List<MobArticleWrapper> articleWrappers = new ArrayList<>();

        Iterables.addAll(articleWrappers,
                articles.stream()
                        .map(webElement -> new MobArticleWrapper(baseFunctions, webElement))
                        .collect(Collectors.toList()));

        return articleWrappers;
    }

    public MobArticleWrapper getArticleByTitle(String title) {
        LOGGER.info("Getting article by title");
        Optional<MobArticleWrapper> wrapper = Iterables.tryFind(getAllArticles(),
                mobArticleWrapper -> title.equals(mobArticleWrapper.getArticleTitle()));
        return wrapper.isPresent() ? wrapper.get() : null;
    }

    /*public MobArticlePage openArticleByTitle(String title) {
        LOGGER.info("Opening article by clicking on title");
        getArticleByTitle(title).clickOnTitle();
        return new MobArticlePage(baseFunctions);
    }*/
}
