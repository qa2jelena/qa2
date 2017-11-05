package delfiTestPageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunctions {

    private WebDriver driver;
    private static final String FIREFOX_DRIVER_PATH = "C:/Users/adminpc/Desktop/geckodriver.exe";
    private static final Logger LOGGER = LogManager.getLogger(BaseFunctions.class);

    public BaseFunctions() {
        LOGGER.info("Setting system properties");
        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);

        LOGGER.info("Starting Firefox driver");
        this.driver = new FirefoxDriver();

        LOGGER.info("Maximize browser window");
        driver.manage().window().maximize();
    }

    public void goToURL(String url) {
        LOGGER.info("Open URL: " + url);
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public List<WebElement> getElements(By locator) {
        LOGGER.info("Getting elements");
        return driver.findElements(locator);
    }

    public WebElement getElement(By locator) {
        Assert.assertTrue("Element is not present on page", isPresentElement(locator));
        LOGGER.info("Getting element");
        return driver.findElement(locator);
    }

    public void clickElement(By locator) {
        LOGGER.info("Click element");
        driver.findElement(locator).click();
    }

    public boolean isPresentElement(By locator) {
        LOGGER.info("Checking element presence");
        List<WebElement> elements = driver.findElements(locator);
        return elements.size() != 0;
    }

    public boolean isDisplayedElement(By locator) {
        LOGGER.info("Checking element displaying");
        return driver.findElement(locator).isDisplayed();
    }

    public void waitForElement(By locator, int seconds) {
        LOGGER.info("Waiting for element");
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void quitDriver() {
        LOGGER.info("Quiting driver");
        driver.quit();
    }

    public void scrollToElement(By locator) {
        LOGGER.info("Scrolling to element");
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
