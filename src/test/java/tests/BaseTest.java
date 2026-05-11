package tests;

import drivers.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://qa-stellarburgers.education-services.ru/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}