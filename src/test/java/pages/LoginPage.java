package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Поле "Email" — ищем по типу текстового поля внутри блока с классом input_type_text
    @FindBy(xpath = "//div[contains(@class, 'input_type_text')]//input")
    private WebElement emailInput;

    // Поле "Пароль" — внутри блока с классом input_type_password
    @FindBy(xpath = "//div[contains(@class, 'input_type_password')]//input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text()='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        // Ждём, пока страница логина загрузится и поле email станет видимым
        wait.until(ExpectedConditions.urlContains("/login"));
        wait.until(ExpectedConditions.visibilityOf(emailInput));
    }

    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(loginButton);
    }

    public void clickRegisterLink() {
        click(registerLink);
    }

    public void clickForgotPasswordLink() {
        click(forgotPasswordLink);
    }
}