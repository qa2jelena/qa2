package pageObjectFirstTest.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {
    BaseFunctions baseFunctions;
    private static final By ARTICLE = By.xpath("//a[@class='top2012-title']");
    private static final By TITLE = By.xpath(".//h3/a");
    private static final By COMMENT_COUNT = By.xpath("//h3/a[@class='comment-count");
    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    public HomePage(BaseFunctions bs) {
        this.baseFunctions = bs;
    }

    public WebElement getFirstArticle() {
        LOGGER.info("Getting first article on home page");
        return baseFunctions.getElement(ARTICLE);
    }

    public String getTitle(WebElement article) {
        LOGGER.info("Getting title");
        return article.findElement(TITLE).getText();
    }

    public int getTCommentCount(WebElement article) {
        LOGGER.info("Getting comment count");
        String countText = article.findElement(COMMENT_COUNT).getText();
        //dobavitj logiku otbrasivanija skobok
        return Integer.valueOf(countText);
    }

    public ArticlePage openArticle() {
        LOGGER.info("Click title");
        baseFunctions.clickElement(TITLE);
        return new ArticlePage(baseFunctions);
    }
}
