package tests;

import api.UserApiClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;
import utils.UserGenerator;


@ExtendWith(AllureJunit5.class)
@DisplayName("Тесты входа в аккаунт")
public class LoginTest extends BaseTest {

    private UserApiClient userApiClient;
    private String accessToken;
    private String email;
    private String password;

    @BeforeEach
    public void createUser() {
        userApiClient = new UserApiClient();
        String name = UserGenerator.generateRandomName();
        email = UserGenerator.generateRandomEmail();
        password = UserGenerator.generateValidPassword();
        var response = userApiClient.createUser(name, email, password);
        accessToken = response.jsonPath().getString("accessToken");
    }

    @AfterEach
    public void deleteUser() {
        if (accessToken != null && userApiClient != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Step("Проверка загрузки главной страницы")
    private void assertMainPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.xpath("//h1[text()='Соберите бургер']")));
    }

    @Test
    @Step("Тест: вход через кнопку «Войти в аккаунт» на главной странице")
    public void loginViaMainButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButtonOnMain();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        assertMainPageLoaded();
    }

    @Test
    @Step("Тест: вход через кнопку «Личный кабинет»")
    public void loginViaPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        assertMainPageLoaded();
    }

    @Test
    @Step("Тест: вход через кнопку в форме регистрации")
    public void loginViaRegisterFormTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginPage.login(email, password);
        assertMainPageLoaded();
    }

    @Test
    @Step("Тест: вход через кнопку в форме восстановления пароля")
    public void loginViaForgotPasswordFormTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();
        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();
        loginPage.login(email, password);
        assertMainPageLoaded();
    }
}