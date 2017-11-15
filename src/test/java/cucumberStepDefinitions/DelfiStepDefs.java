package cucumberStepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.mobPages.MobHomePage;
import delfiTestPageObject.webPages.WebHomePage;
import delfiTestPageObject.wrappers.MobArticleWrapper;
import delfiTestPageObject.wrappers.WebArticleWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class DelfiStepDefs {

    private BaseFunctions webBaseFunctions = new BaseFunctions();
    private BaseFunctions mobBaseFunctions = new BaseFunctions();
    private static final String WEB_HOME_PAGE_URL = "http://rus.delfi.lv";
    private static final String MOB_HOME_PAGE_URL = "http://m.rus.delfi.lv/";
    private static final Logger LOGGER = LogManager.getLogger(DelfiStepDefs.class);

    private String articleTitleForTest;
    private WebHomePage webHomePage;
    private MobHomePage mobHomePage;
    private WebArticleWrapper webArticleWrapper;
    private MobArticleWrapper mobArticleWrapper;
    private Integer webHomePageCommentCount, mobHomePageCommentCount;


    @Given("Article title for test: (.*)")
    public void article_title_for_test(String title) {
        articleTitleForTest = title;
        LOGGER.info("Article title for test: " + articleTitleForTest);
    }

    @When("Open web home page")
    public void open_web_home_page() {
        LOGGER.info("Opening web home page");
        webBaseFunctions.goToURL(WEB_HOME_PAGE_URL);
        webHomePage = new WebHomePage(webBaseFunctions);
    }

    @When("Open mob home page")
    public void open_mob_home_page() {
        LOGGER.info("Opening mob home page");
        mobBaseFunctions.goToURL(MOB_HOME_PAGE_URL);
        mobHomePage = new MobHomePage(mobBaseFunctions);
    }

    @When("Get article by title on web home page")
    public void get_article_by_title_on_web_home_page() {

        LOGGER.info("Getting article on web home page");
        webArticleWrapper = webHomePage.getArticleByTitle(articleTitleForTest);

        LOGGER.info("Checking article existence on web home page");
        Assert.assertTrue("[WEB HOME PAGE] Article with title \"" + articleTitleForTest + "\" not found", webArticleWrapper != null);
    }

    @When("Get article comment count on web home page")
    public void get_article_comment_count_on_web_home_page() {
        LOGGER.info("Getting article comment count on web home page");
        webHomePageCommentCount = webArticleWrapper.getCommentCount();
    }

    @When("Get article by title on mob home page")
    public void get_article_by_title_on_mob_home_page() {

        LOGGER.info("Getting article on mob home page");
        mobArticleWrapper = mobHomePage.getArticleByTitle(articleTitleForTest);

        LOGGER.info("Checking article existence on mob home page");
        Assert.assertTrue("[MOB HOME PAGE] Article with title \"" + articleTitleForTest + "\" not found", mobArticleWrapper != null);
    }

    @When("Get article comment count on mob home page")
    public void get_article_comment_count_on_mob_home_page() {
        LOGGER.info("Getting article comment count on mob home page");
        mobHomePageCommentCount = mobArticleWrapper.getCommentCount();
    }

    @Then("Compare article comment counts on web and mob home page")
    public void compare_article_comment_counts_on_web_and_mob_home_page() {

        System.out.println("\nArticle comment counts on home pages:");
        System.out.println("web: " + webHomePageCommentCount);
        System.out.println("mob: " + mobHomePageCommentCount + "\n");

        LOGGER.info("Comparing article comment counts on web and mob home page");
        int deltaForTest = 3;
        String mainCountErrorMessage = "[WEB & MOB HOME] Comment counts is not equals";
        Assert.assertEquals(mainCountErrorMessage, webHomePageCommentCount, mobHomePageCommentCount, deltaForTest);

        LOGGER.info("***** TEST IS SUCCESSFUL! *****");
    }

    @Then("Quit browser windows")
    public void quit_browser_windows() {
        LOGGER.info("Quiting web browser window");
        webBaseFunctions.quitDriver();
        LOGGER.info("Quiting mob browser window");
        mobBaseFunctions.quitDriver();
    }

}
