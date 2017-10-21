package pageObjectFirstTest.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseFunctions {
    WebDriver driver;
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

    public WebElement getElement(By locator) {
        LOGGER.info("Getting element");
        return driver.findElement(locator);
    }

    public void clickElement(By locator) {
        LOGGER.info("Click element");
        driver.findElement(locator).click();
    }
}
