package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameInput;

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//label[text()='Пароль']/following-sibling::input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//p[contains(@class, 'input__error')]")
    private WebElement passwordError;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public RegisterPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    public void register(String name, String email, String password) {
        sendKeys(nameInput, name);
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(registerButton);
    }

    public void clickLoginLink() {
        click(loginLink);
    }

    public boolean isPasswordErrorDisplayed() {
        try {
            return passwordError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPasswordErrorText() {
        return getText(passwordError);
    }
}