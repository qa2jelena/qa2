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

import java.util.ArrayList;
import java.util.List;

public class FirstDelfiTest {

    private BaseFunctions webBaseFunctions = new BaseFunctions();
    private BaseFunctions mobBaseFunctions = new BaseFunctions();
    private CompareHelper compareHelper = new CompareHelper();
    private static final Logger LOGGER = LogManager.getLogger(FirstDelfiTest.class);
    private static final String WEB_HOME_PAGE_URL = "http://rus.delfi.lv";
    private static final String MOB_HOME_PAGE_URL = "http://m.rus.delfi.lv/";

    /**
     * Давайте проверим Delfi.lv
     * Напишите тест, который проверит первые 5 статей основной и мобильной версии.
     * Проверять нужно на совпадение заголовков, количества комментариев
     * Проверяем на главной странице, странице самой статьи, и на странице комментариев (и для полной версии, и для мобильной)
     * make this test stable – if item count in each category will change test will still work
     */
    @Test
    public void delfiTest() {

        int articleCountToTest = 5;
        LOGGER.info("Article count to test: " + articleCountToTest);

        LOGGER.info("Opening web home page");
        webBaseFunctions.goToURL(WEB_HOME_PAGE_URL);

        LOGGER.info("Opening mob home page");
        mobBaseFunctions.goToURL(MOB_HOME_PAGE_URL);

        /*
          WEB HOME PAGE
         */
        LOGGER.info("Getting article list on web home page");
        WebHomePage webHomePage = new WebHomePage(webBaseFunctions);
        List<WebElement> webArticleList = webHomePage.getAllArticlesList();
        LOGGER.info("Found " + webArticleList.size() + " articles on web home page");

        LOGGER.info("Getting article titles, IDs and comment counts on web home page");
        List<String> webHomeTitles = new ArrayList<String>();
        List<Integer> webHomeCommentCounts = new ArrayList<Integer>();

        List<Long> webHomeIDs = new ArrayList<Long>();

        for (int i = 0; i < articleCountToTest; i++) {
            LOGGER.info("Getting article Nr." + (i + 1));
            WebElement article = webArticleList.get(i);
            // Title:
            webHomeTitles.add(webHomePage.getTitle(article));
            // ID:
            webHomeIDs.add(webHomePage.getID(article));
            // Comment count:
            webHomeCommentCounts.add(webHomePage.getCommentCount(article));
        }
        LOGGER.info("Article titles, IDs and comment counts on web home page gathered!");

        /*
         * MOB HOME PAGE
         */
        LOGGER.info("Getting article list on mob home page:");
        MobHomePage mobHomePage = new MobHomePage(mobBaseFunctions);
        List<WebElement> mobArticleList = mobHomePage.getArticleList();
        LOGGER.info("Found " + mobArticleList.size() + " articles on mob home page");

        LOGGER.info("Getting article titles and comment counts on mob home page");
        List<String> mobHomeTitles = new ArrayList<String>();
        List<Integer> mobHomeCommentCounts = new ArrayList<Integer>();

        for (int i = 0; i < articleCountToTest; i++) {
            LOGGER.info("Getting article Nr." + (i + 1));
            WebElement article = mobArticleList.get(i);
            // Title:
            mobHomeTitles.add(mobHomePage.getTitle(article));
            // Comment count:
            mobHomeCommentCounts.add(mobHomePage.getCommentCount(article));
        }
        LOGGER.info("Article titles and comment counts on mob home page gathered!");

        /*
         * WEB ARTICLE AND COMMENT PAGE
         */
        LOGGER.info("Getting article titles and comment counts on web article and comment page");

        List<String> webArticlePageTitles = new ArrayList<String>();
        List<Integer> webArticlePageCommentCounts = new ArrayList<Integer>();
        List<String> webCommentPageTitles = new ArrayList<String>();
        List<Integer> webCommentPageRegisteredCommentCounts = new ArrayList<Integer>();
        List<Integer> webCommentPageAnonymousCommentCounts = new ArrayList<Integer>();

        int webTimeOutInSeconds = 10;

        // ARTICLE PAGE
        LOGGER.info("Getting article Nr.1");
        LOGGER.info("Opening first web article page by click");
        WebArticlePage webArticlePage = webHomePage.openFirstArticlePage();

        for (int i = 0; i < webHomeTitles.size(); i++) {

            if (i != 0) {
                LOGGER.info("Getting article Nr." + (i + 1));
                LOGGER.info("Opening web article page by link");
                webBaseFunctions.goToURL("http://rus.delfi.lv/d?id=" + webHomeIDs.get(i));
            }

            // Title:
            webArticlePage.waitTitlePresence(webTimeOutInSeconds);
            webArticlePageTitles.add(webArticlePage.getTitle());
            // Comment count:
            webArticlePageCommentCounts.add(webArticlePage.getCommentCount());


            if (webArticlePage.isReadCommentButtonPresent()) {
                // ARTICLE COMMENT PAGE
                LOGGER.info("Opening web article comment page");
                WebCommentPage webCommentPage = webArticlePage.openCommentPage();
                // Title:
                webCommentPage.waitTitlePresence(webTimeOutInSeconds);
                webCommentPageTitles.add(webCommentPage.getTitle());
                // Comment count:
                webCommentPageRegisteredCommentCounts.add(webCommentPage.getRegisteredCommentCount());
                webCommentPageAnonymousCommentCounts.add(webCommentPage.getAnonymousCommentCount());
            } else {
                LOGGER.info("Commenting on the article is forbidden");
                webCommentPageTitles.add("Article without comment page");
                webCommentPageRegisteredCommentCounts.add(0);
                webCommentPageAnonymousCommentCounts.add(0);
            }
        }
        LOGGER.info("Article titles and comment counts on web article and comment page gathered!");

        /*
         * MOB ARTICLE AND COMMENT PAGE
         */
        LOGGER.info("Getting article titles and comment counts on mob article and comment page");

        List<String> mobArticlePageTitles = new ArrayList<String>();
        List<Integer> mobArticlePageCommentCounts = new ArrayList<Integer>();
        List<String> mobCommentPageTitles = new ArrayList<String>();
        List<Integer> mobCommentPageRegisteredCommentCounts = new ArrayList<Integer>();
        List<Integer> mobCommentPageAnonymousCommentCounts = new ArrayList<Integer>();

        int mobTimeOutInSeconds = 10;

        // ARTICLE PAGE
        LOGGER.info("Getting article Nr.1");
        LOGGER.info("Opening first mob article page by click");
        MobArticlePage mobArticlePage = mobHomePage.openFirstArticlePage();

        for (int i = 0; i < webHomeTitles.size(); i++) {

            if (i != 0) {
                LOGGER.info("Getting article Nr." + (i + 1));
                LOGGER.info("Opening mob article page by link");
                mobBaseFunctions.goToURL("http://m.rus.delfi.lv/article.php?id=" + webHomeIDs.get(i));
            }

            // Title:
            mobArticlePage.waitTitlePresence(mobTimeOutInSeconds);
            mobArticlePageTitles.add(mobArticlePage.getTitle());
            // Comment count:
            mobArticlePageCommentCounts.add(mobArticlePage.getCommentCount());


            if (mobArticlePage.isReadCommentLinkPresent()) {
                // ARTICLE COMMENT PAGE
                LOGGER.info("Opening mob article comment page");
                MobCommentPage mobCommentPage = mobArticlePage.openCommentPage();
                // Title:
                mobCommentPage.waitTitlePresence(mobTimeOutInSeconds);
                mobCommentPageTitles.add(mobCommentPage.getTitle());
                // Comment count:
                mobCommentPageRegisteredCommentCounts.add(mobCommentPage.getRegisteredCommentCount());
                mobCommentPageAnonymousCommentCounts.add(mobCommentPage.getAnonymousCommentCount());
            } else {
                LOGGER.info("Commenting on the article is forbidden");
                mobCommentPageTitles.add("Article without comment page");
                mobCommentPageRegisteredCommentCounts.add(0);
                mobCommentPageAnonymousCommentCounts.add(0);
            }
        }
        LOGGER.info("Article titles and comment counts on mob article and comment page gathered!");

        // Visual check
        System.out.println("\nHome pages:");
        System.out.println(webHomeTitles);
        System.out.println(webHomeCommentCounts);
        System.out.println(mobHomeTitles);
        System.out.println(mobHomeCommentCounts);

        System.out.println("\nArticle pages:");
        System.out.println(webArticlePageTitles);
        System.out.println(webArticlePageCommentCounts);
        System.out.println(mobArticlePageTitles);
        System.out.println(mobArticlePageCommentCounts);

        System.out.println("\nComment pages:");
        System.out.println(webCommentPageTitles);
        System.out.println(webCommentPageRegisteredCommentCounts);
        System.out.println(webCommentPageAnonymousCommentCounts);
        System.out.println(mobCommentPageTitles);
        System.out.println(mobCommentPageRegisteredCommentCounts);
        System.out.println(mobCommentPageAnonymousCommentCounts);

        System.out.println("\nIDs:");
        System.out.println(webHomeIDs);

        int deltaForTest = 3;

        for (int i = 0; i < webHomeTitles.size(); i++) {
            LOGGER.info("Checking article Nr." + (i + 1));

            /*
             * HOME PAGES
             */
            // Titles:
            LOGGER.info("Comparing article titles on web and mob home page");
            String homeTitleErrorMessage = "[WEB & MOB HOME] Titles is not equals for an article: ";
            compareHelper.compareTitles(homeTitleErrorMessage, webHomeTitles.get(i), mobHomeTitles.get(i));
            // Comment count:
            LOGGER.info("Comparing article comment count on web and mob home page");
            String homeCountErrorMessage = "[WEB & MOB HOME] Comment counts is not equals for an article";
            Assert.assertEquals(homeCountErrorMessage + webHomeTitles.get(i),
                    webHomeCommentCounts.get(i), mobHomeCommentCounts.get(i),
                    deltaForTest);

            /*
             * ARTICLE PAGES
             */
            // Titles:
            LOGGER.info("Comparing article titles on web and mob article page");
            String articleTitleErrorMessage = "[WEB & MOB ARTICLE] Titles is not equals for an article: ";
            compareHelper.compareTitles(articleTitleErrorMessage, webArticlePageTitles.get(i), mobArticlePageTitles.get(i));
            // Comment count:
            LOGGER.info("Comparing article comment count on web and mob article page");
            String articleCountErrorMessage = "[WEB & MOB ARTICLE] Comment counts is not equals for an article";
            Assert.assertEquals(articleCountErrorMessage + webArticlePageTitles.get(i),
                    webArticlePageCommentCounts.get(i), mobArticlePageCommentCounts.get(i),
                    deltaForTest);

            /*
             * COMMENT PAGES
             */
            // Titles:
            LOGGER.info("Comparing article titles on web and mob article comment page");
            String commentPageTitleErrorMessage = "[WEB & MOB COMMENT PAGE] Titles is not equals for an article: ";
            compareHelper.compareTitles(commentPageTitleErrorMessage, webCommentPageTitles.get(i), mobCommentPageTitles.get(i));
            // Comment count:
            LOGGER.info("Comparing article comment count on web and mob article comment page");
            String commentPageCountErrorMessage = "[WEB & MOB COMMENT PAGE] Comment count is not equals for an article";
            Assert.assertEquals(commentPageCountErrorMessage + webCommentPageTitles.get(i),
                    webCommentPageRegisteredCommentCounts.get(i) + webCommentPageAnonymousCommentCounts.get(i),
                    mobCommentPageRegisteredCommentCounts.get(i) + mobCommentPageAnonymousCommentCounts.get(i),
                    deltaForTest);
        }

        LOGGER.info("Quiting web browser window");
        webBaseFunctions.quitDriver();
        LOGGER.info("Quiting mob browser window");
        mobBaseFunctions.quitDriver();

        LOGGER.info("***** TEST IS SUCCESSFUL! *****");
    }
}
