package delfiTestPageObject.tests;

import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.helpers.CompareHelper;
import delfiTestPageObject.mobPages.MobArticlePage;
import delfiTestPageObject.mobPages.MobCommentPage;
import delfiTestPageObject.mobPages.MobHomePage;
import delfiTestPageObject.webPages.WebArticlePage;
import delfiTestPageObject.webPages.WebCommentPage;
import delfiTestPageObject.webPages.WebHomePage;
import delfiTestPageObject.wrappers.MobArticleWrapper;
import delfiTestPageObject.wrappers.WebArticleWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class SecondDelfiTestWithWrappers {

    private BaseFunctions webBaseFunctions = new BaseFunctions();
    private BaseFunctions mobBaseFunctions = new BaseFunctions();
    private CompareHelper compareHelper = new CompareHelper();
    private static final Logger LOGGER = LogManager.getLogger(SecondDelfiTestWithWrappers.class);
    private static final String WEB_HOME_PAGE_URL = "http://rus.delfi.lv";
    private static final String MOB_HOME_PAGE_URL = "http://m.rus.delfi.lv/";

    private static final String ARTICLE_TITLE_FOR_TEST = "Латвию ждет перегрев на рынке труда: составлен список из 303 \"дефицитных\" профессий";

    @Test
    public void wrapperDelfiTest() {

        LOGGER.info("Article title for test: " + ARTICLE_TITLE_FOR_TEST);

        LOGGER.info("Opening web home page");
        webBaseFunctions.goToURL(WEB_HOME_PAGE_URL);

        LOGGER.info("Opening mob home page");
        mobBaseFunctions.goToURL(MOB_HOME_PAGE_URL);

        /*
         *  WEB
         */
        String webArticlePageTitle, webCommentPageTitle;
        Integer webHomePageCommentCount, webArticlePageCommentCount, webCommentPageRegisteredCommentCount, webCommentPageAnonymousCommentCount;

        int webTimeOutInSeconds = 10;

        // WEB HOME PAGE
        LOGGER.info("Getting article on web home page");
        WebHomePage webHomePage = new WebHomePage(webBaseFunctions);
        WebArticleWrapper webArticleWrapper = webHomePage.getArticleByTitle(ARTICLE_TITLE_FOR_TEST);

        LOGGER.info("Checking article existence on web home page");
        Assert.assertTrue("[WEB HOME PAGE] Article with title \"" + ARTICLE_TITLE_FOR_TEST + "\" not found", webArticleWrapper != null);

        LOGGER.info("Getting article comment count on web home page");
        webHomePageCommentCount = webArticleWrapper.getCommentCount();

        LOGGER.info("Article comment count on web home page gathered!");

        // WEB ARTICLE PAGE
        LOGGER.info("Opening article page by clicking article title on web home page");
        WebArticlePage webArticlePage = webArticleWrapper.openArticlePage();

        LOGGER.info("Getting article title on web article page");
        webArticlePage.waitTitlePresence(webTimeOutInSeconds);
        webArticlePageTitle = webArticlePage.getTitle();

        LOGGER.info("Getting article comment count on web article page");
        webArticlePageCommentCount = webArticlePage.getCommentCount();

        LOGGER.info("Article title and comment count on web article page gathered!");

        // WEB ARTICLE COMMENT PAGE
        if (webArticlePage.isReadCommentButtonPresent()) {

            LOGGER.info("Opening web article comment page");
            WebCommentPage webCommentPage = webArticlePage.openCommentPage();

            LOGGER.info("Getting article title on web comment page");
            webCommentPage.waitTitlePresence(webTimeOutInSeconds);
            webCommentPageTitle = webCommentPage.getTitle();

            LOGGER.info("Getting article comment count on web comment page");
            webCommentPageRegisteredCommentCount = webCommentPage.getRegisteredCommentCount();
            webCommentPageAnonymousCommentCount = webCommentPage.getAnonymousCommentCount();

        } else {
            LOGGER.info("Commenting on the article is forbidden");
            webCommentPageTitle = "Article without comment page";
            webCommentPageRegisteredCommentCount = 0;
            webCommentPageAnonymousCommentCount = 0;
        }
        LOGGER.info("Article title and comment count on web comment page gathered!");

        /*
         *  MOB
         */
        String mobArticlePageTitle, mobCommentPageTitle;
        Integer mobHomePageCommentCount, mobArticlePageCommentCount, mobCommentPageRegisteredCommentCount, mobCommentPageAnonymousCommentCount;

        int mobTimeOutInSeconds = 10;

        // MOB HOME PAGE
        LOGGER.info("Getting article on mob home page");
        MobHomePage mobHomePage = new MobHomePage(mobBaseFunctions);
        MobArticleWrapper mobArticleWrapper = mobHomePage.getArticleByTitle(ARTICLE_TITLE_FOR_TEST);

        LOGGER.info("Checking article existence on mob home page");
        Assert.assertTrue("[MOB HOME PAGE] Article with title \"" + ARTICLE_TITLE_FOR_TEST + "\" not found", mobArticleWrapper != null);

        LOGGER.info("Getting article comment count on mob home page");
        mobHomePageCommentCount = mobArticleWrapper.getCommentCount();

        LOGGER.info("Article comment count on mob home page gathered!");

        // MOB ARTICLE PAGE
        LOGGER.info("Opening article page by clicking article title on mob home page");
        MobArticlePage mobArticlePage = mobArticleWrapper.openArticlePage();

        LOGGER.info("Getting article title on mob article page");
        mobArticlePage.waitTitlePresence(mobTimeOutInSeconds);
        mobArticlePageTitle = mobArticlePage.getTitle();

        LOGGER.info("Getting article comment count on mob article page");
        mobArticlePageCommentCount = mobArticlePage.getCommentCount();

        LOGGER.info("Article title and comment count on mob article page gathered!");

        // MOB ARTICLE COMMENT PAGE
        if (mobArticlePage.isReadCommentLinkPresent()) {

            LOGGER.info("Opening mob article comment page");
            MobCommentPage mobCommentPage = mobArticlePage.openCommentPage();

            LOGGER.info("Getting article title on mob comment page");
            mobCommentPage.waitTitlePresence(mobTimeOutInSeconds);
            mobCommentPageTitle = mobCommentPage.getTitle();

            LOGGER.info("Getting article comment count on mob comment page");
            mobCommentPageRegisteredCommentCount = mobCommentPage.getRegisteredCommentCount();
            mobCommentPageAnonymousCommentCount = mobCommentPage.getAnonymousCommentCount();

        } else {
            LOGGER.info("Commenting on the article is forbidden");
            mobCommentPageTitle = "Article without comment page";
            mobCommentPageRegisteredCommentCount = 0;
            mobCommentPageAnonymousCommentCount = 0;
        }
        LOGGER.info("Article title and comment count on mob comment page gathered!");

        // Visual check
        System.out.println("\nHome pages:");
        System.out.println(ARTICLE_TITLE_FOR_TEST);
        System.out.println(webHomePageCommentCount);
        System.out.println(ARTICLE_TITLE_FOR_TEST);
        System.out.println(mobHomePageCommentCount);

        System.out.println("\nArticle pages:");
        System.out.println(webArticlePageTitle);
        System.out.println(webArticlePageCommentCount);
        System.out.println(mobArticlePageTitle);
        System.out.println(mobArticlePageCommentCount);

        System.out.println("\nComment pages:");
        System.out.println(webCommentPageTitle);
        System.out.println(webCommentPageRegisteredCommentCount);
        System.out.println(webCommentPageAnonymousCommentCount);
        System.out.println(mobCommentPageTitle);
        System.out.println(mobCommentPageRegisteredCommentCount);
        System.out.println(mobCommentPageAnonymousCommentCount);

        int deltaForTest = 3;

        /*
         * HOME PAGES
         */
        LOGGER.info("Comparing article comment counts on web and mob home page");
        String mainCountErrorMessage = "[WEB & MOB HOME] Comment counts is not equals";
        Assert.assertEquals(mainCountErrorMessage, webHomePageCommentCount, mobHomePageCommentCount, deltaForTest);

        /*
         * ARTICLE PAGES
         */
        LOGGER.info("Comparing article titles on web and mob article page");
        String articleTitleErrorMessage = "[WEB & MOB ARTICLE] Titles is not equals";
        compareHelper.compareTitles(articleTitleErrorMessage, webArticlePageTitle, mobArticlePageTitle);

        LOGGER.info("Comparing article comment count on web and mob article page");
        String articleCountErrorMessage = "[WEB & MOB ARTICLE] Comment counts is not equals";
        Assert.assertEquals(articleCountErrorMessage, webArticlePageCommentCount, mobArticlePageCommentCount, deltaForTest);

        /*
         * COMMENT PAGES
         */
        LOGGER.info("Comparing article titles on web and mob article comment page");
        String commentPageTitleErrorMessage = "[WEB & MOB COMMENT PAGE] Titles is not equals";
        compareHelper.compareTitles(commentPageTitleErrorMessage, webCommentPageTitle, mobCommentPageTitle);

        LOGGER.info("Comparing article comment count on web and mob article comment page");
        String commentPageCountErrorMessage = "[WEB & MOB COMMENT PAGE] Comment count is not equals";
        Assert.assertEquals(commentPageCountErrorMessage,
                webCommentPageRegisteredCommentCount + webCommentPageAnonymousCommentCount,
                mobCommentPageRegisteredCommentCount + mobCommentPageAnonymousCommentCount,
                deltaForTest);

        LOGGER.info("Quiting web browser window");
        webBaseFunctions.quitDriver();
        LOGGER.info("Quiting mob browser window");
        mobBaseFunctions.quitDriver();

        LOGGER.info("***** TEST IS SUCCESSFUL! *****");
    }
}
