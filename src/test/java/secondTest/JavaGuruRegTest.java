package secondTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * So, first homework: need to test registration form on javaGuru.lv
 * would be nice to check availability of all major block on pages
 */

public class JavaGuruRegTest {
    private static final By QA_COURSE_MENU_LINK = By.xpath(".//span[text()='Курс Тестирования(QA)']");
    private static final By ERROR_FIELD_TEXT = By.xpath(".//div[text()='Šis ir obligāts jautājums.']");
    private static final By QA_REGISTRATION_BTN = By.className("regCourseA");
    private static final By SEND_BTN = By.id("ss-submit");
    private static final By NAME_FIELD = By.id("entry_1000000");
    private static final By SURNAME_FIELD = By.id("entry_1000001");
    private static final By PHONE_FIELD = By.id("entry_1000002");
    private static final By EMAIL_FIELD = By.id("entry_1000003");
    private static final By QA1_RADIO_BTN = By.id("group_738971122_1");
    private static final By QA2_RADIO_BTN = By.id("group_738971122_2");
    private static final By SKILL_TEXTAREA = By.id("entry_1000004");
    private static final By YES_NOTEBOOK_RADIO_BTN = By.id("group_1887084786_1");
    private static final By NO_NOTEBOOK_RADIO_BTN = By.id("group_1887084786_2");
    private static final By RECOMMENDATION_TEXTAREA = By.id("entry_1696450117");
    private static final By COMMENTS_TEXTAREA = By.id("entry_1000006");
    private static final By DISCOUNT_FIELD = By.id("entry_1091055623");
    private static final By RESPONSE_TEXT = By.xpath(".//div[text()='Your response has been recorded.']");
    private String HOME_PAGE = "http://javaguru.lv";

    @Test
    public void javaGuruRegTest() {
        System.setProperty("webdriver.gecko.driver", "C:/Users/adminpc/Desktop/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get(HOME_PAGE);
        driver.findElement(QA_COURSE_MENU_LINK).click();
        driver.findElement(QA_REGISTRATION_BTN).click();

        ArrayList<String> tabs = new ArrayList<String>();
        while (tabs.size() != 2) {
            tabs.clear();
            tabs.addAll(driver.getWindowHandles());
        }
        driver.switchTo().window(tabs.get(1));

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement sendButton = wait.until(ExpectedConditions.presenceOfElementLocated(SEND_BTN));
        sendButton.click();
        Assert.assertTrue("No required field error message", driver.findElements(ERROR_FIELD_TEXT).size() > 0);

        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(NAME_FIELD));
        nameField.clear();
        nameField.sendKeys("Name");

        WebElement surnameField = wait.until(ExpectedConditions.presenceOfElementLocated(SURNAME_FIELD));
        surnameField.clear();
        surnameField.sendKeys("Surname");

        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(PHONE_FIELD));
        phoneField.clear();
        phoneField.sendKeys("12345678");

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys("name.surname@mail.test");

        WebElement qa1RadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(QA1_RADIO_BTN));
        qa1RadioButton.click();

        WebElement qa2RadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(QA2_RADIO_BTN));
        qa2RadioButton.click();

        WebElement skillTextarea = wait.until(ExpectedConditions.presenceOfElementLocated(SKILL_TEXTAREA));
        skillTextarea.clear();
        skillTextarea.sendKeys("skill");

        WebElement yesNotebookRadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(YES_NOTEBOOK_RADIO_BTN));
        yesNotebookRadioButton.click();

        WebElement noNotebookRadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(NO_NOTEBOOK_RADIO_BTN));
        noNotebookRadioButton.click();

        WebElement recommendationTextarea = wait.until(ExpectedConditions.presenceOfElementLocated(RECOMMENDATION_TEXTAREA));
        recommendationTextarea.clear();
        recommendationTextarea.sendKeys("recommendation");

        WebElement commentsTextarea = wait.until(ExpectedConditions.presenceOfElementLocated(COMMENTS_TEXTAREA));
        commentsTextarea.clear();
        commentsTextarea.sendKeys("comments");

        WebElement discountField = wait.until(ExpectedConditions.presenceOfElementLocated(DISCOUNT_FIELD));
        discountField.clear();
        discountField.sendKeys("code");

        sendButton.click();
        Assert.assertTrue("Something goes wrong on submitting!", driver.findElements(RESPONSE_TEXT).size() > 0);

        driver.quit();
    }
}
