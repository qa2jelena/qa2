package delfiTestPageObject.webPages;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.helpers.NumberFromTextHelper;
import delfiTestPageObject.wrappers.WebArticleWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebHomePage {

    private BaseFunctions baseFunctions;
    private NumberFromTextHelper numberFromTextHelper = new NumberFromTextHelper();
    private static final By LOGO_HEADER = By.xpath(".//a[@class='headerLogo']");
    private static final By TOP_CONTENT_HEADER = By.xpath(".//div[@class='headerTopContent']");
    private static final By MAIN_NAVIGATION_HEADER = By.xpath(".//nav[@class='headerMainNavigation headerSeparatedNav']");
    private static final By CHANNEL_CATEGORIES_HEADER = By.xpath(".//nav[@class='headerSeparatedNav headerChannelCategories ']");
    private static final By COPYRIGHT_BLOCK = By.xpath(".//div[@class='copyright-block']");
    private static final By TOP_ARTICLE = By.xpath(".//h3[@class='top2012-title']");
    private static final By ARTICLE1 = By.xpath(".//div[contains(@class,'content-twothirds-block')]");
    private static final By ARTICLE2 = By.xpath(".//div[contains(@class,'content-third-block')]");
    private static final By ARTICLE3 = By.xpath(".//div[contains(@class,'content-half-block')]");
    private static final By ARTICLE4 = By.xpath(".//div[contains(@class,'article-full-image')]");
    private static final By ARTICLE5 = By.xpath(".//div[contains(@class,'article-link')]");
    private static final By TITLE = By.xpath(".//a[contains(@class,'title')]");
    private static final By COMMENT_COUNT = By.xpath(".//a[contains(@class,'comment-count')]");
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

        LOGGER.info("Checking for the presence of top articles");
        Assert.assertTrue("No top articles on web home page!", baseFunctions.isPresentElement(TOP_ARTICLE));

        LOGGER.info("Checking for the presence of copyright block");
        Assert.assertTrue("No copyright block on web home page!", baseFunctions.isPresentElement(COPYRIGHT_BLOCK));
    }

    /*public List<WebElement> getTopArticlesList() {
        LOGGER.info("Getting top article list");
        return baseFunctions.getElements(TOP_ARTICLE);
    }*/

    public List<WebElement> getAllArticlesList() {
        LOGGER.info("Scrolling page for all articles appeared");
        baseFunctions.scrollToElement(COPYRIGHT_BLOCK);
        baseFunctions.scrollToElement(COPYRIGHT_BLOCK);

        LOGGER.info("Getting all article list");
        List<WebElement> articles = new ArrayList<>();
        articles.addAll(baseFunctions.getElements(TOP_ARTICLE));
        articles.addAll(baseFunctions.getElements(ARTICLE1));
        articles.addAll(baseFunctions.getElements(ARTICLE2));
        articles.addAll(baseFunctions.getElements(ARTICLE3));
        articles.addAll(baseFunctions.getElements(ARTICLE4));
        articles.addAll(baseFunctions.getElements(ARTICLE5));

        LOGGER.info("Found " + articles.size() + " articles on web home page");
        return articles;
    }

    public String getTitle(WebElement article) {
        LOGGER.info("Getting article title");
        return article.findElement(TITLE).getText();
    }

    public Long getID(WebElement article) {
        LOGGER.info("Getting article ID");
        return numberFromTextHelper.getLongFromString(article.findElement(TITLE).getAttribute("href"));
    }

    public Integer getCommentCount(WebElement article) {
        LOGGER.info("Getting article comment count");
        return article.findElements(COMMENT_COUNT).isEmpty() ? 0 : numberFromTextHelper.getIntegerFromString(article.findElement(COMMENT_COUNT).getText());
    }

    public WebArticlePage openFirstArticlePage() {
        LOGGER.info("Click first article title");
        baseFunctions.clickElement(TITLE);
        return new WebArticlePage(baseFunctions);
    }

    // FOR WRAPPERS:

    private List<WebArticleWrapper> getAllArticles() {
        LOGGER.info("Getting all article list");
        List<WebElement> articles = getAllArticlesList();

        LOGGER.info("Converting each article from article list to article wrapper, and adding it to article wrapper list");
        List<WebArticleWrapper> articleWrappers = new ArrayList<>();

        Iterables.addAll(articleWrappers,
                articles.stream()
                        .map(webElement -> new WebArticleWrapper(baseFunctions, webElement))
                        .collect(Collectors.toList()));

        return articleWrappers;
    }

    public WebArticleWrapper getArticleByTitle(String title) {
        LOGGER.info("Getting article by title");
        Optional<WebArticleWrapper> wrapper = Iterables.tryFind(getAllArticles(),
                webArticleWrapper -> title.equals(webArticleWrapper.getArticleTitle()));
        return wrapper.isPresent() ? wrapper.get() : null;
    }

    /*public WebArticlePage openArticleByTitle(String title) {
        LOGGER.info("Opening article page by clicking on title");
        getArticleByTitle(title).clickOnTitle();
        return new WebArticlePage(baseFunctions);
    }*/


}
