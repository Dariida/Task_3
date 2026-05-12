package tests;

import api.UserApiClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import utils.UserGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
@DisplayName("Тесты личного кабинета и навигации")
public class AccountNavigationTest extends BaseTest {

    private UserApiClient userApiClient;
    private String accessToken;
    private String email;
    private String password;

    @BeforeEach
    public void createUserAndLogin() {
        userApiClient = new UserApiClient();
        String name = UserGenerator.generateRandomName();
        email = UserGenerator.generateRandomEmail();
        password = UserGenerator.generateValidPassword();
        var response = userApiClient.createUser(name, email, password);
        accessToken = response.jsonPath().getString("accessToken");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButtonOnMain();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.xpath("//h1[text()='Соберите бургер']")));
    }

    @AfterEach
    public void deleteUser() {
        if (accessToken != null && userApiClient != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Test
    @Step("Тест: переход в личный кабинет по клику на ссылку «Личный кабинет»")
    public void goToPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        AccountPage accountPage = new AccountPage(driver);
        assertTrue(accountPage.isAccountPageDisplayed(), "Страница личного кабинета не открылась");
    }

    @Test
    @Step("Тест: переход из личного кабинета в конструктор по кнопке «Конструктор»")
    public void goToConstructorFromAccountTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        MainPage afterClick = new MainPage(driver);
        afterClick.clickConstructorLink();
        assertTrue(afterClick.isConstructorLinkActive() && afterClick.isMainPageDisplayed(),
                "Не произошёл переход на главную страницу конструктора");
    }

    @Test
    @Step("Тест: переход из личного кабинета на главную по клику на логотип")
    public void goToMainViaLogoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        MainPage afterClick = new MainPage(driver);
        afterClick.clickLogo();
        assertTrue(afterClick.isConstructorLinkActive() && afterClick.isMainPageDisplayed(),
                "Логотип не вернул на главную страницу");
    }

    @Test
    @Step("Тест: выход из аккаунта через кнопку «Выйти» в личном кабинете")
    public void logoutTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoutButton();
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"), "После выхода не перебросило на страницу логина");
    }
}