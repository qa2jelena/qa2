package delfiTestPageObject.tests;

import delfiTestPageObject.BaseFunctions;
import delfiTestPageObject.helpers.CompareHelper;
import delfiTestPageObject.mobPages.MobArticlePage;
import delfiTestPageObject.mobPages.MobCommentPage;
import delfiTestPageObject.mobPages.MobHomePage;
import delfiTestPageObject.webPages.WebArticlePage;
import delfiTestPageObject.webPages.WebCommentPage;
import delfiTestPageObject.webPages.WebHomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class SecondDelfiTest {

    private BaseFunctions webBaseFunctions = new BaseFunctions();
    private BaseFunctions mobBaseFunctions = new BaseFunctions();
    private CompareHelper compareHelper = new CompareHelper();
    private static final Logger LOGGER = LogManager.getLogger(SecondDelfiTest.class);
    private static final String WEB_HOME_PAGE_URL = "http://rus.delfi.lv";
    private static final String MOB_HOME_PAGE_URL = "http://m.rus.delfi.lv/";

    /**
     * Pishem vtoroj test, mozhem v tom zhe klasse.
     * Zadanie: V peremennoj ja ukazivaju tekst toj statji, kotoruju ja hochu proverith,
     * ljubuju iz 300 na glavnoj stranice web versii delfi,
     * Vash test berjot etot zagolovok, ishet etu statju
     * i proveraet praviljnostj nazvanija i kolichestvo kommentariev
     * i v WEB i v Mobile versii sajta, na glavnih stranicah, stranicah samoj statji i stranicah komentariev :)
     */
    @Test
    public void delfiTest() {

        // Article title for test:
        String articleTitleForTest = "Реконструкция Кр. Барона: улица все еще не принята в эксплуатацию";
        LOGGER.info("Article title for test: " + articleTitleForTest);

        LOGGER.info("Opening web home page");
        webBaseFunctions.goToURL(WEB_HOME_PAGE_URL);

        LOGGER.info("Opening mob home page");
        mobBaseFunctions.goToURL(MOB_HOME_PAGE_URL);

        /*
         * WEB HOME PAGE
         */
        HashMap<String, Integer> webArticlesTitlesAndCounts = new HashMap<>();
        HashMap<String, Long> webArticlesTitlesAndIDs = new HashMap<>();

        LOGGER.info("Getting article list on web home page");
        WebHomePage webHomePage = new WebHomePage(webBaseFunctions);
        List<WebElement> webArticleList = webHomePage.getAllArticlesList();

        LOGGER.info("Getting article titles, IDs and comment counts on web home page");
        for (WebElement article : webArticleList) {
            LOGGER.info("Getting article Nr." + (webArticleList.indexOf(article) + 1));
            // Title:
            String articleTitle = webHomePage.getTitle(article);
            // ID:
            Long articleID = webHomePage.getID(article);
            // Comment count:
            Integer articleCommentCount = webHomePage.getCommentCount(article);

            // Adding elements to HashMap:
            webArticlesTitlesAndCounts.put(articleTitle, articleCommentCount);
            webArticlesTitlesAndIDs.put(articleTitle, articleID);
        }
        LOGGER.info("Article titles, IDs and comment counts on web home page gathered!");

        /*
         * MOB HOME PAGE
         */
        HashMap<String, Integer> mobArticlesTitlesAndCounts = new HashMap<>();

        LOGGER.info("Getting article list on mob home page:");
        MobHomePage mobHomePage = new MobHomePage(mobBaseFunctions);
        List<WebElement> mobArticleList = mobHomePage.getArticleList();

        LOGGER.info("Getting article titles and comment counts on mob home page");
        for (WebElement article : mobArticleList) {
            LOGGER.info("Getting article Nr." + (mobArticleList.indexOf(article) + 1));
            // Title:
            String articleTitle = mobHomePage.getTitle(article);
            // Comment count:
            Integer articleCommentCount = mobHomePage.getCommentCount(article);

            // Adding elements to HashMap:
            mobArticlesTitlesAndCounts.put(articleTitle, articleCommentCount);
        }
        LOGGER.info("Article titles and comment counts on mob home page gathered!");

        /*
         * Checking article existence
         */
        LOGGER.info("Checking article existence on web home page");
        Assert.assertTrue("[WEB HOME PAGE] Article with title \"" + articleTitleForTest + "\" not found",
                webArticlesTitlesAndCounts.containsKey(articleTitleForTest));

        LOGGER.info("Checking article existence on mob home page");
        Assert.assertTrue("[MOB HOME PAGE] Article with title \"" + articleTitleForTest + "\" not found",
                mobArticlesTitlesAndCounts.containsKey(articleTitleForTest));

        Integer webHomePageCommentCount = webArticlesTitlesAndCounts.get(articleTitleForTest);
        Integer mobHomePageCommentCount = mobArticlesTitlesAndCounts.get(articleTitleForTest);

        Long articleID = webArticlesTitlesAndIDs.get(articleTitleForTest);

        /*
         * WEB ARTICLE AND COMMENT PAGE
         */
        LOGGER.info("Getting article titles and comment counts on web article and comment page");

        String webArticlePageTitle, webCommentPageTitle;
        Integer webArticlePageCommentCount, webCommentPageRegisteredCommentCount, webCommentPageAnonymousCommentCount;

        int webTimeOutInSeconds = 10;

        // ARTICLE PAGE
        LOGGER.info("Opening web article page by link");
        webBaseFunctions.goToURL("http://rus.delfi.lv/d?id=" + articleID);
        WebArticlePage webArticlePage = new WebArticlePage(webBaseFunctions);
        // Title:
        webArticlePage.waitTitlePresence(webTimeOutInSeconds);
        webArticlePageTitle = webArticlePage.getTitle();
        // Comment count:
        webArticlePageCommentCount = webArticlePage.getCommentCount();

        if (webArticlePage.isReadCommentButtonPresent()) {
            // ARTICLE COMMENT PAGE
            LOGGER.info("Opening web article comment page");
            WebCommentPage webCommentPage = webArticlePage.openCommentPage();
            // Title:
            webCommentPage.waitTitlePresence(webTimeOutInSeconds);
            webCommentPageTitle = webCommentPage.getTitle();
            // Comment count:
            webCommentPageRegisteredCommentCount = webCommentPage.getRegisteredCommentCount();
            webCommentPageAnonymousCommentCount = webCommentPage.getAnonymousCommentCount();
        } else {
            LOGGER.info("Commenting on the article is forbidden");
            webCommentPageTitle = "Article without comment page";
            webCommentPageRegisteredCommentCount = 0;
            webCommentPageAnonymousCommentCount = 0;
        }
        LOGGER.info("Article titles and comment counts on web article and comment page gathered!");

        /*
         * MOB ARTICLE AND COMMENT PAGE
         */
        LOGGER.info("Getting article titles and comment counts on mob article and comment page");

        String mobArticlePageTitle, mobCommentPageTitle;
        Integer mobArticlePageCommentCount, mobCommentPageRegisteredCommentCount, mobCommentPageAnonymousCommentCount;

        int mobTimeOutInSeconds = 10;

        // ARTICLE PAGE
        LOGGER.info("Opening mob article page by link");
        mobBaseFunctions.goToURL("http://m.rus.delfi.lv/article.php?id=" + articleID);
        MobArticlePage mobArticlePage = new MobArticlePage(mobBaseFunctions);
        // Title:
        mobArticlePage.waitTitlePresence(mobTimeOutInSeconds);
        mobArticlePageTitle = mobArticlePage.getTitle();
        // Comment count:
        mobArticlePageCommentCount = mobArticlePage.getCommentCount();

        if (mobArticlePage.isReadCommentLinkPresent()) {
            // ARTICLE COMMENT PAGE
            LOGGER.info("Opening mob article comment page");
            MobCommentPage mobCommentPage = mobArticlePage.openCommentPage();
            // Title:
            mobCommentPage.waitTitlePresence(mobTimeOutInSeconds);
            mobCommentPageTitle = mobCommentPage.getTitle();
            // Comment count:
            mobCommentPageRegisteredCommentCount = mobCommentPage.getRegisteredCommentCount();
            mobCommentPageAnonymousCommentCount = mobCommentPage.getAnonymousCommentCount();
        } else {
            LOGGER.info("Commenting on the article is forbidden");
            mobCommentPageTitle = "Article without comment page";
            mobCommentPageRegisteredCommentCount = 0;
            mobCommentPageAnonymousCommentCount = 0;
        }
        LOGGER.info("Article titles and comment counts on mob article and comment page gathered!");

        // Visual check
        System.out.println("\nHome pages:");
        System.out.println(articleTitleForTest);
        System.out.println(webHomePageCommentCount);
        System.out.println(articleTitleForTest);
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

        System.out.println("\nID:");
        System.out.println(articleID);

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
