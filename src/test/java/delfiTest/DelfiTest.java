package delfiTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DelfiTest {
    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']");
    private static final By MAIN_ARTICLE_TITLE = By.xpath("a[contains(@class,'top2012-title')]");
    private static final By MAIN_ARTICLE_COMMENT_COUNT = By.xpath("a[contains(@class,'comment-count')]");
    private static final By ARTICLE_TITLE = By.xpath(".//h1/span");
    private static final By ARTICLE_COMMENT_COUNT = By.xpath("//*[@id='article']//a[contains(@class,'comment-count')]");
    private static final By ARTICLE_COMMENT_FORM = By.xpath("//*[@id='comment-dark-skin-wrapper']");
    private static final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1/a");
    private static final By COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT = By.xpath("//*[@id='comments-listing']//a[contains(text(),'Зарегистрированные ')]/span");
    private static final By COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT = By.xpath("//*[@id='comments-listing']//a[contains(text(),'Анонимные ')]/span");

    private static final By MOBILE_ARTICLE = By.className("md-mosaic-title");
    private static final By MOBILE_MAIN_ARTICLE_TITLE = By.xpath("a[contains(@class,'md-scrollpos')]");
    private static final By MOBILE_MAIN_ARTICLE_COMMENT_COUNT = By.xpath("a[contains(@class,'commentCount')]");
    private static final By MOBILE_ARTICLE_TITLE = By.xpath(".//h1");
    private static final By MOBILE_ARTICLE_COMMENT_COUNT = By.xpath("//*[@id='article']//a[contains(@class,'commentCount')]");
    private static final By MOBILE_ARTICLE_COMMENT_FORM = By.xpath("//*[@id='comment-dark-skin-wrapper']");
    private static final By MOBILE_COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1//a//span[contains(@class,'text')]");
    private static final By MOBILE_COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT = By.xpath("//*[@id='comments-listing']//a[contains(text(),'Зарегистрированные ')]/span");
    private static final By MOBILE_COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT = By.xpath("//*[@id='comments-listing']//a[contains(text(),'Анонимные ')]/span");

    private static final Logger LOGGER = LogManager.getLogger(DelfiTest.class);

    private String HOME_PAGE = "http://rus.delfi.lv";
    private String MOBILE_HOME_PAGE = "http://m.rus.delfi.lv/";

    @Test
    /**
     * Pishem vtoroj test, mozhem v tom zhe klasse.
     * Zadanie: V peremennoj ja ukazivaju tekst toj statji, kotoruju ja hochu proverith,
     * ljubuju iz 300 na glavnoj stranice web versii delfi,
     * Vash test berjot etot zagolovok, ishet etu statju
     * i proveraet praviljnostj nazvanija i kolichestvo kommentariev
     * i v WEB i v Mobile versii sajta, na glavnih stranicah, stranicah samoj statji i stranicah komentariev :)
     */
    public void secondDelfiTest() {
        LOGGER.info("Starting our test!");

        // Article title for test:
        String articleTitleForTest = "Трамп заявил, что у него для Северной Кореи остался лишь один вариант";
        LOGGER.info("Article title for test: " + articleTitleForTest);

        System.setProperty("webdriver.gecko.driver", "C:/Users/adminpc/Desktop/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        WebDriver mobileDriver = new FirefoxDriver();
        mobileDriver.manage().window().maximize();

        LOGGER.info("Opening web home page " + HOME_PAGE);
        driver.get(HOME_PAGE);
        LOGGER.info("Opening mob home page " + MOBILE_HOME_PAGE);
        mobileDriver.get(MOBILE_HOME_PAGE);

        List<WebElement> articleList = driver.findElements(ARTICLE);
        LOGGER.info("Found " + articleList.size() + " articles on web main page");
        List<WebElement> mobileArticleList = mobileDriver.findElements(MOBILE_ARTICLE);
        LOGGER.info("Found " + mobileArticleList.size() + " articles on mob main page");

        /**
         * WEB VERSION MAIN PAGE
         */
        HashMap<String, Integer> articlesTitlesAndCounts = new HashMap<String, Integer>();

        HashMap<String, Long> articlesTitlesAndIDs = new HashMap<String, Long>();
        Long idToCheck;

        LOGGER.info("Getting article titles and comment counts on web main page");
        for (int i = 0; i < articleList.size(); i++) {
            WebElement element = articleList.get(i);
            // Title:
            String mainTitle = element.findElement(MAIN_ARTICLE_TITLE).getText();
            // ID:
            String idToParse = element.findElement(MAIN_ARTICLE_TITLE).getAttribute("href");
            idToParse = idToParse.substring(idToParse.indexOf('=') + 1);
            idToCheck = Long.valueOf(idToParse);
            // Comment count:
            int mainCount = 0;
            if (element.findElements(MAIN_ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElement(MAIN_ARTICLE_COMMENT_COUNT).getText();
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
                mainCount = Integer.valueOf(countToParse);
            }
            // Adding element to HashMap:
            articlesTitlesAndCounts.put(mainTitle, mainCount);
            articlesTitlesAndIDs.put(mainTitle, idToCheck);
        }
        LOGGER.info("Article titles and comment counts on web main page collected");

        /**
         * MOB VERSION MAIN PAGE
         */
        HashMap<String, Integer> mobileArticlesTitlesAndCounts = new HashMap<String, Integer>();

        LOGGER.info("Getting article titles and comment counts on mob main page");
        for (int i = 0; i < mobileArticleList.size(); i++) {
            WebElement element = mobileArticleList.get(i);
            // Title:
            String mobileMainTitle = element.findElement(MOBILE_MAIN_ARTICLE_TITLE).getText();
            // Comment count:
            int mobileMainCount = 0;
            if (element.findElements(MOBILE_MAIN_ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElement(MOBILE_MAIN_ARTICLE_COMMENT_COUNT).getText();
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
                mobileMainCount = Integer.valueOf(countToParse);
            }
            // Adding element to HashMap:
            mobileArticlesTitlesAndCounts.put(mobileMainTitle, mobileMainCount);
        }
        LOGGER.info("Article titles and comment counts on mob main page collected");

        // Checking article existence
        LOGGER.info("Checking article existence on web main page");
        Assert.assertTrue("[FULL MAIN PAGE] Article with title " + articleTitleForTest + " not found", articlesTitlesAndCounts.containsKey(articleTitleForTest));
        LOGGER.info("Checking article existence on mob main page");
        Assert.assertTrue("[MOBILE MAIN PAGE] Article with title " + articleTitleForTest + " not found", mobileArticlesTitlesAndCounts.containsKey(articleTitleForTest));

        int mainCount = articlesTitlesAndCounts.get(articleTitleForTest);
        int mobileMainCount = mobileArticlesTitlesAndCounts.get(articleTitleForTest);

        Long articleID = articlesTitlesAndIDs.get(articleTitleForTest);

        /**
         * WEB VERSION ARTICLE PAGE AND ARTICLE COMMENT PAGE
         */
        String title, commentPageTitle;
        int count, commentPageCount;

        WebDriverWait wait = new WebDriverWait(driver, 10);
        // ARTICLE PAGE
        LOGGER.info("Opening article web page");
        driver.get("http://rus.delfi.lv/d?id=" + articleID);
        // Title:
        LOGGER.info("Getting article title on web article page");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_TITLE));
        title = element.getText();
        // Comment count:
        LOGGER.info("Getting article comment count on web article page");
        if (element.findElements(ARTICLE_COMMENT_COUNT).size() != 0) {
            String countToParse = element.findElement(ARTICLE_COMMENT_COUNT).getText();
            countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
            count = Integer.valueOf(countToParse);
        } else {
            count = 0;
        }

        LOGGER.info("Checking if the article is allowed to comment");
        if (element.findElements(ARTICLE_COMMENT_FORM).size() != 0) {
            LOGGER.info("Commenting is allowed");
            // ARTICLE COMMENT PAGE
            LOGGER.info("Opening article web comment page");
            driver.get("http://rus.delfi.lv/d?id=" + articleID + "&com=1&reg=1&no=0&s=1");
            // Title:
            LOGGER.info("Getting article title on web article comment page");
            WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated(COMMENT_PAGE_ARTICLE_TITLE));
            commentPageTitle = element2.getText();
            if (commentPageTitle.contains(": комментарии 1-")) {
                commentPageTitle = commentPageTitle.substring(0, commentPageTitle.indexOf(": комментарии 1-"));
            }
            // Comment count:
            LOGGER.info("Getting article comment count on web article comment page");
            int regCount = 0, anonCount = 0;
            if (element2.findElements(COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).size() != 0) {
                String regCountToParse = element2.findElement(COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).getText();
                regCountToParse = regCountToParse.substring(regCountToParse.indexOf('(') + 1, regCountToParse.indexOf(')'));
                regCount = Integer.valueOf(regCountToParse);
            }
            if (element2.findElements(COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).size() != 0) {
                String anonCountToParse = element2.findElement(COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).getText();
                anonCountToParse = anonCountToParse.substring(anonCountToParse.indexOf('(') + 1, anonCountToParse.indexOf(')'));
                anonCount = Integer.valueOf(anonCountToParse);
            }
            commentPageCount = regCount + anonCount;
        } else {
            LOGGER.info("Commenting not allowed");
            commentPageTitle = "{Article without comment page}";
            commentPageCount = 0;
        }

        /**
         * MOB VERSION ARTICLE PAGE AND ARTICLE COMMENT PAGE
         */
        String mobileTitle, mobileCommentPageTitle;
        int mobileCount, mobileCommentPageCount;

        WebDriverWait mobileWait = new WebDriverWait(mobileDriver, 10);
        // ARTICLE PAGE
        LOGGER.info("Opening article mob page");
        mobileDriver.get("http://m.rus.delfi.lv/article.php?id=" + articleID);
        // Title:
        LOGGER.info("Getting article title on mob article page");
        WebElement mobileElement = mobileWait.until(ExpectedConditions.presenceOfElementLocated(MOBILE_ARTICLE_TITLE));
        mobileTitle = mobileElement.getText();
        // Comment count:
        LOGGER.info("Getting article comment count on mob article page");
        if (mobileElement.findElements(MOBILE_ARTICLE_COMMENT_COUNT).size() != 0) {
            String countToParse = mobileElement.findElement(MOBILE_ARTICLE_COMMENT_COUNT).getText();
            countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
            mobileCount = Integer.valueOf(countToParse);
        } else {
            mobileCount = 0;
        }

        LOGGER.info("Checking if the article is allowed to comment");
        if (mobileElement.findElements(MOBILE_ARTICLE_COMMENT_FORM).size() != 0) {
            LOGGER.info("Commenting is allowed");
            // ARTICLE COMMENT PAGE
            LOGGER.info("Opening article mob comment page");
            mobileDriver.get("http://m.rus.delfi.lv/article.php?id=" + articleID + "&com=1&reg=1&no=0&s=1");
            //Title:
            LOGGER.info("Getting article title on mob article comment page");
            WebElement element2 = mobileWait.until(ExpectedConditions.presenceOfElementLocated(MOBILE_COMMENT_PAGE_ARTICLE_TITLE));
            mobileCommentPageTitle = element2.getText();
            // Comment count:
            LOGGER.info("Getting article comment count on mob article comment page");
            int regCount = 0, anonCount = 0;
            if (element2.findElements(MOBILE_COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).size() != 0) {
                String regCountToParse = element2.findElement(MOBILE_COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).getText();
                regCountToParse = regCountToParse.substring(regCountToParse.indexOf('(') + 1, regCountToParse.indexOf(')'));
                regCount = Integer.valueOf(regCountToParse);
            }
            if (element2.findElements(MOBILE_COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).size() != 0) {
                String anonCountToParse = element2.findElement(MOBILE_COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).getText();
                anonCountToParse = anonCountToParse.substring(anonCountToParse.indexOf('(') + 1, anonCountToParse.indexOf(')'));
                anonCount = Integer.valueOf(anonCountToParse);
            }
            mobileCommentPageCount = regCount + anonCount;
        } else {
            LOGGER.info("Commenting not allowed");
            mobileCommentPageTitle = "{Article without comment page}";
            mobileCommentPageCount = 0;
        }

        // Visual control
        System.out.println("\nMain pages:");
        System.out.println(mainCount);
        System.out.println(mobileMainCount);

        System.out.println("\nArticle pages:");
        System.out.println(title);
        System.out.println(count);
        System.out.println(mobileTitle);
        System.out.println(mobileCount);

        System.out.println("\nComment pages:");
        System.out.println(commentPageTitle);
        System.out.println(commentPageCount);
        System.out.println(mobileCommentPageTitle);
        System.out.println(mobileCommentPageCount);

        System.out.println("\nID:");
        System.out.println(articleID);

        int deltaForTest = 3;

        /**
         * MAIN PAGES
         */
        // Comment count:
        LOGGER.info("Comparing article comment count on web and mob main page");
        String mainCountErrorMessage = "[WEB & MOB MAIN] Comment count is not equals: ";
        Assert.assertEquals(mainCountErrorMessage, mainCount, mobileMainCount, deltaForTest);

        /**
         * ARTICLE PAGES
         */
        // Titles:
        LOGGER.info("Comparing article titles on web and mob article page");
        String articleTitleErrorMessage = "[WEB & MOB ARTICLE] Title is not equals: ";
        if (title.length() == mobileTitle.length()) {
            Assert.assertEquals(articleTitleErrorMessage, title, mobileTitle);
        } else if (title.length() > mobileTitle.length()) {
            Assert.assertTrue(articleTitleErrorMessage, title.matches("(.*)" + mobileTitle + "(.*)"));
        } else {
            Assert.assertTrue(articleTitleErrorMessage, mobileTitle.matches("(.*)" + title + "(.*)"));
        }
        // Comment count:
        LOGGER.info("Comparing article comment count on web and mob article page");
        String articleCountErrorMessage = "[WEB & MOB ARTICLE] Comment count is not equals: ";
        Assert.assertEquals(articleCountErrorMessage, count, mobileCount, deltaForTest);

        /**
         * COMMENT PAGES
         */
        // Titles:
        LOGGER.info("Comparing article titles on web and mob article comment page");
        String commentPageTitleErrorMessage = "[WEB & MOB COMMENT PAGE] Title is not equals: ";
        if (commentPageTitle.length() == mobileCommentPageTitle.length()) {
            Assert.assertEquals(commentPageTitleErrorMessage, commentPageTitle, mobileCommentPageTitle);
        } else if (commentPageTitle.length() > mobileCommentPageTitle.length()) {
            Assert.assertTrue(commentPageTitleErrorMessage, commentPageTitle.matches("(.*)" + mobileCommentPageTitle + "(.*)"));
        } else {
            Assert.assertTrue(commentPageTitleErrorMessage, mobileCommentPageTitle.matches("(.*)" + commentPageTitle + "(.*)"));
        }
        // Comment count:
        LOGGER.info("Comparing article comment count on web and mob article comment page");
        String commentPageCountErrorMessage = "[WEB & MOB COMMENT PAGE] Comment count is not equals: ";
        Assert.assertEquals(commentPageCountErrorMessage, commentPageCount, mobileCommentPageCount, deltaForTest);

        driver.quit();
        mobileDriver.quit();
    }

    @Test
    /**
     * Давайте проверим Delfi.lv
     * Напишите тест, который проверит первые 5 статей основной и мобильной версии.
     * Проверять нужно на совпадение заголовков, количества комментариев
     * Проверяем на главной странице, странице самой статьи, и на странице комментариев (и для полной версии, и для мобильной)
     * make this test stable – if item count in each category will change test will still work
     */
    public void delfiTest() {
        LOGGER.info("Starting our test!");
        System.setProperty("webdriver.gecko.driver", "C:/Users/adminpc/Desktop/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        WebDriver mobileDriver = new FirefoxDriver();
        mobileDriver.manage().window().maximize();

        LOGGER.info("Opening web home page " + HOME_PAGE);
        driver.get(HOME_PAGE);
        LOGGER.info("Opening mob home page " + MOBILE_HOME_PAGE);
        mobileDriver.get(MOBILE_HOME_PAGE);

        List<WebElement> articleList = driver.findElements(ARTICLE);
        LOGGER.info("Found " + articleList.size() + " articles on web main page");
        List<WebElement> mobileArticleList = mobileDriver.findElements(MOBILE_ARTICLE);
        LOGGER.info("Found " + mobileArticleList.size() + " articles on mob main page");

        /**
         * WEB VERSION MAIN PAGE
         */
        List<String> mainTitles = new ArrayList<String>();
        List<Integer> mainCounts = new ArrayList<Integer>();

        List<Long> idsToCheck = new ArrayList<Long>();

        int articleCountToTest = 5;
        LOGGER.info("Article count to test: " + articleCountToTest);

        LOGGER.info("Getting article titles and comment counts on web main page");
        for (int i = 0; i < articleCountToTest; i++) {
            WebElement element = articleList.get(i);
            // Title:
            mainTitles.add(element.findElement(MAIN_ARTICLE_TITLE).getText());
            // ID:
            String idToParse = element.findElement(MAIN_ARTICLE_TITLE).getAttribute("href");
            idToParse = idToParse.substring(idToParse.indexOf('=') + 1);
            idsToCheck.add(Long.valueOf(idToParse));
            // Comment count:
            if (element.findElements(MAIN_ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElement(MAIN_ARTICLE_COMMENT_COUNT).getText();
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
                mainCounts.add(Integer.valueOf(countToParse));
            } else {
                mainCounts.add(0);
            }
        }
        LOGGER.info("Article titles and comment counts on web main page collected");

        /**
         * MOB VERSION MAIN PAGE
         */
        List<String> mobileMainTitles = new ArrayList<String>();
        List<Integer> mobileMainCounts = new ArrayList<Integer>();

        LOGGER.info("Getting article titles and comment counts on mob main page");
        for (int i = 0; i < articleCountToTest; i++) {
            WebElement element = mobileArticleList.get(i);
            // Title:
            mobileMainTitles.add(element.findElement(MOBILE_MAIN_ARTICLE_TITLE).getText());
            // Comment count:
            if (element.findElements(MOBILE_MAIN_ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElement(MOBILE_MAIN_ARTICLE_COMMENT_COUNT).getText();
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
                mobileMainCounts.add(Integer.valueOf(countToParse));
            } else {
                mobileMainCounts.add(0);
            }
        }
        LOGGER.info("Article titles and comment counts on mob main page collected");

        /**
         * WEB VERSION ARTICLE PAGE AND ARTICLE COMMENT PAGE
         */
        List<String> titles = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        List<String> commentPageTitles = new ArrayList<String>();
        List<Integer> commentPageCounts = new ArrayList<Integer>();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        LOGGER.info("Getting article titles and comment counts on web article and comment page");
        for (int i = 0; i < mainTitles.size(); i++) {
            // ARTICLE PAGE
            driver.get("http://rus.delfi.lv/d?id=" + idsToCheck.get(i));
            // Title:
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_TITLE));
            titles.add(element.getText());
            // Comment count:
            if (element.findElements(ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElement(ARTICLE_COMMENT_COUNT).getText();
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
                counts.add(Integer.valueOf(countToParse));
            } else {
                counts.add(0);
            }

            if (element.findElements(ARTICLE_COMMENT_FORM).size() != 0) {
                // ARTICLE COMMENT PAGE
                driver.get("http://rus.delfi.lv/d?id=" + idsToCheck.get(i) + "&com=1&reg=1&no=0&s=1");
                // Title:
                WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated(COMMENT_PAGE_ARTICLE_TITLE));
                String titleToParse = element2.getText();
                if (titleToParse.contains(": комментарии 1-")) {
                    titleToParse = titleToParse.substring(0, titleToParse.indexOf(": комментарии 1-"));
                }
                commentPageTitles.add(titleToParse);
                // Comment count:
                int regCount = 0, anonCount = 0;
                if (element2.findElements(COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).size() != 0) {
                    String regCountToParse = element2.findElement(COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).getText();
                    regCountToParse = regCountToParse.substring(regCountToParse.indexOf('(') + 1, regCountToParse.indexOf(')'));
                    regCount = Integer.valueOf(regCountToParse);
                }
                if (element2.findElements(COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).size() != 0) {
                    String anonCountToParse = element2.findElement(COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).getText();
                    anonCountToParse = anonCountToParse.substring(anonCountToParse.indexOf('(') + 1, anonCountToParse.indexOf(')'));
                    anonCount = Integer.valueOf(anonCountToParse);
                }
                commentPageCounts.add(regCount + anonCount);
            } else {
                commentPageTitles.add("{Article without comment page}");
                commentPageCounts.add(0);
            }
        }
        LOGGER.info("Article titles and comment counts on web article and comment page collected");

        /**
         * MOB VERSION ARTICLE PAGE AND ARTICLE COMMENT PAGE
         */
        List<String> mobileTitles = new ArrayList<String>();
        List<Integer> mobileCounts = new ArrayList<Integer>();
        List<String> mobileCommentPageTitles = new ArrayList<String>();
        List<Integer> mobileCommentPageCounts = new ArrayList<Integer>();

        WebDriverWait mobileWait = new WebDriverWait(mobileDriver, 10);
        LOGGER.info("Getting article titles and comment counts on mob article and comment page");
        for (int i = 0; i < mainTitles.size(); i++) {
            // ARTICLE PAGE
            mobileDriver.get("http://m.rus.delfi.lv/article.php?id=" + idsToCheck.get(i));
            // Title:
            WebElement element = mobileWait.until(ExpectedConditions.presenceOfElementLocated(MOBILE_ARTICLE_TITLE));
            mobileTitles.add(element.getText());
            // Comment count:
            if (element.findElements(MOBILE_ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElement(MOBILE_ARTICLE_COMMENT_COUNT).getText();
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')'));
                mobileCounts.add(Integer.valueOf(countToParse));
            } else {
                mobileCounts.add(0);
            }

            if (element.findElements(MOBILE_ARTICLE_COMMENT_FORM).size() != 0) {
                // ARTICLE COMMENT PAGE
                mobileDriver.get("http://m.rus.delfi.lv/article.php?id=" + idsToCheck.get(i) + "&com=1&reg=1&no=0&s=1");
                //Title:
                WebElement element2 = mobileWait.until(ExpectedConditions.presenceOfElementLocated(MOBILE_COMMENT_PAGE_ARTICLE_TITLE));
                mobileCommentPageTitles.add(element2.getText());
                // Comment count:
                int regCount = 0, anonCount = 0;
                if (element2.findElements(MOBILE_COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).size() != 0) {
                    String regCountToParse = element2.findElement(MOBILE_COMMENT_PAGE_ARTICLE_REG_COMMENT_COUNT).getText();
                    regCountToParse = regCountToParse.substring(regCountToParse.indexOf('(') + 1, regCountToParse.indexOf(')'));
                    regCount = Integer.valueOf(regCountToParse);
                }
                if (element2.findElements(MOBILE_COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).size() != 0) {
                    String anonCountToParse = element2.findElement(MOBILE_COMMENT_PAGE_ARTICLE_ANON_COMMENT_COUNT).getText();
                    anonCountToParse = anonCountToParse.substring(anonCountToParse.indexOf('(') + 1, anonCountToParse.indexOf(')'));
                    anonCount = Integer.valueOf(anonCountToParse);
                }
                mobileCommentPageCounts.add(regCount + anonCount);
            } else {
                mobileCommentPageTitles.add("{Article without comment page}");
                mobileCommentPageCounts.add(0);
            }
        }
        LOGGER.info("Article titles and comment counts on mob article and comment page collected");

        // Visual control
        System.out.println("\nMain pages:");
        System.out.println(mainTitles);
        System.out.println(mainCounts);
        System.out.println(mobileMainTitles);
        System.out.println(mobileMainCounts);

        System.out.println("\nArticle pages:");
        System.out.println(titles);
        System.out.println(counts);
        System.out.println(mobileTitles);
        System.out.println(mobileCounts);

        System.out.println("\nComment pages:");
        System.out.println(commentPageTitles);
        System.out.println(commentPageCounts);
        System.out.println(mobileCommentPageTitles);
        System.out.println(mobileCommentPageCounts);

        System.out.println("\nIDs:");
        System.out.println(idsToCheck);

        int deltaForTest = 3;

        for (int i = 0; i < mainTitles.size(); i++) {

            /**
             * MAIN PAGES
             */
            // Titles:
            LOGGER.info("Comparing article titles on web and mob main page");
            String mainTitleErrorMessage = "[WEB & MOB MAIN] Title is not equals for an article: ";
            if (titles.get(i).length() == mobileTitles.get(i).length()) {
                Assert.assertEquals(mainTitleErrorMessage + mainTitles.get(i), mainTitles.get(i), mobileMainTitles.get(i));
            } else if (titles.get(i).length() > mobileTitles.get(i).length()) {
                Assert.assertTrue(mainTitleErrorMessage + mainTitles.get(i), mainTitles.get(i).matches("(.*)" + mobileMainTitles.get(i) + "(.*)"));
            } else {
                Assert.assertTrue(mainTitleErrorMessage + mainTitles.get(i), mobileMainTitles.get(i).matches("(.*)" + mainTitles.get(i) + "(.*)"));
            }
            // Comment count:
            LOGGER.info("Comparing article comment count on web and mob main page");
            String mainCountErrorMessage = "[WEB & MOB MAIN] Comment count is not equals for an article";
            Assert.assertEquals(mainCountErrorMessage + mainTitles.get(i), mainCounts.get(i), mobileMainCounts.get(i), deltaForTest);

            /**
             * ARTICLE PAGES
             */
            // Titles:
            LOGGER.info("Comparing article titles on web and mob article page");
            String articleTitleErrorMessage = "[WEB & MOB ARTICLE] Title is not equals for an article: ";
            if (titles.get(i).length() == mobileTitles.get(i).length()) {
                Assert.assertEquals(articleTitleErrorMessage + titles.get(i), titles.get(i), mobileTitles.get(i));
            } else if (titles.get(i).length() > mobileTitles.get(i).length()) {
                Assert.assertTrue(articleTitleErrorMessage + titles.get(i), titles.get(i).matches("(.*)" + mobileTitles.get(i) + "(.*)"));
            } else {
                Assert.assertTrue(articleTitleErrorMessage + titles.get(i), mobileTitles.get(i).matches("(.*)" + titles.get(i) + "(.*)"));
            }
            // Comment count:
            LOGGER.info("Comparing article comment count on web and mob article page");
            String articleCountErrorMessage = "[WEB & MOB ARTICLE] Comment count is not equals for an article";
            Assert.assertEquals(articleCountErrorMessage + titles.get(i), counts.get(i), mobileCounts.get(i), deltaForTest);

            /**
             * COMMENT PAGES
             */
            // Titles:
            LOGGER.info("Comparing article titles on web and mob article comment page");
            String commentPageTitleErrorMessage = "[WEB & MOB COMMENT PAGE] Title is not equals for an article: ";
            if (commentPageTitles.get(i).length() == mobileCommentPageTitles.get(i).length()) {
                Assert.assertEquals(commentPageTitleErrorMessage + commentPageTitles.get(i), commentPageTitles.get(i), mobileCommentPageTitles.get(i));
            } else if (commentPageTitles.get(i).length() > mobileCommentPageTitles.get(i).length()) {
                Assert.assertTrue(commentPageTitleErrorMessage + commentPageTitles.get(i), commentPageTitles.get(i).matches("(.*)" + mobileCommentPageTitles.get(i) + "(.*)"));
            } else {
                Assert.assertTrue(commentPageTitleErrorMessage + commentPageTitles.get(i), mobileCommentPageTitles.get(i).matches("(.*)" + commentPageTitles.get(i) + "(.*)"));
            }
            // Comment count:
            LOGGER.info("Comparing article comment count on web and mob article comment page");
            String commentPageCountErrorMessage = "[WEB & MOB COMMENT PAGE] Comment count is not equals for an article";
            Assert.assertEquals(commentPageCountErrorMessage + commentPageTitles.get(i), commentPageCounts.get(i), mobileCommentPageCounts.get(i), deltaForTest);
        }

        driver.quit();
        mobileDriver.quit();
    }
}
