package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage {

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginLink() {
        click(loginLink);
    }
}