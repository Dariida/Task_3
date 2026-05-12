package tests;

import api.UserApiClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.UserGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
@DisplayName("Тесты регистрации")
public class RegistrationTest extends BaseTest {

    private UserApiClient userApiClient;
    private String accessToken;

    @AfterEach
    public void deleteUser() {
        if (accessToken != null && userApiClient != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Test
    @Step("Тест: успешная регистрация нового пользователя")
    public void successfulRegistrationTest() {
        userApiClient = new UserApiClient();
        String name = UserGenerator.generateRandomName();
        String email = UserGenerator.generateRandomEmail();
        String password = UserGenerator.generateValidPassword();

        registerNewUser(name, email, password);
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"), "Не перешли на страницу входа");

        // Логинимся через API, чтобы получить токен для удаления
        var loginResponse = userApiClient.login(email, password);
        accessToken = loginResponse.jsonPath().getString("accessToken");
    }

    @Test
    @Step("Тест: ошибка при регистрации с паролем менее 6 символов")
    public void registrationWithInvalidPasswordTest() {
        String name = UserGenerator.generateRandomName();
        String email = UserGenerator.generateRandomEmail();
        String invalidPassword = UserGenerator.generateInvalidPassword();

        registerNewUser(name, email, invalidPassword);
        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue(registerPage.isPasswordErrorDisplayed(), "Ошибка для пароля < 6 символов не отображается");
    }

    @Step("Регистрация пользователя {name}, {email}, {password} через UI")
    private void registerNewUser(String name, String email, String password) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);
    }
}