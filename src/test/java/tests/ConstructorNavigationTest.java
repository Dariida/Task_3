package tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
@DisplayName("Тесты навигации по конструктору")
public class ConstructorNavigationTest extends BaseTest {

    @Test
    @Step("Тест: переход к разделу «Булки»")
    public void switchToBunsTest() {
        MainPage mainPage = new MainPage(driver);
        // Переключаемся на соусы, чтобы гарантированно уйти с булок
        mainPage.clickSaucesTab();
        // Теперь возвращаемся на булки
        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsTabActive(), "Раздел «Булки» не активен");
    }

    @Test
    @Step("Тест: переход к разделу «Соусы»")
    public void switchToSaucesTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isSaucesTabActive(), "Раздел «Соусы» не активен");
    }

    @Test
    @Step("Тест: переход к разделу «Начинки»")
    public void switchToFillingsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isFillingsTabActive(), "Раздел «Начинки» не активен");
    }
}