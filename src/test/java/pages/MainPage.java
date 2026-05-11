package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButtonMain;

    @FindBy(xpath = "//a[@href='/account']")
    private WebElement personalAccountLink;

    @FindBy(xpath = "//a[@href='/' and contains(@class, 'AppHeader_header__link')]")
    private WebElement constructorLink;

    @FindBy(xpath = "//div[contains(@class, 'AppHeader_header__logo')]/a")
    private WebElement logoLink;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab') and span[text()='Булки']]")
    private WebElement bunsTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab') and span[text()='Соусы']]")
    private WebElement saucesTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab') and span[text()='Начинки']]")
    private WebElement fillingsTab;

    @FindBy(xpath = "//h2[text()='Булки']")
    private WebElement bunsHeader;

    @FindBy(xpath = "//h2[text()='Соусы']")
    private WebElement saucesHeader;

    @FindBy(xpath = "//h2[text()='Начинки']")
    private WebElement fillingsHeader;

    public MainPage(WebDriver driver) {
        super(driver);
        // Не ждём ничего в конструкторе – ожидания будут только в методах
    }

    public void clickLoginButtonOnMain() {
        click(loginButtonMain);
    }

    public void clickPersonalAccount() {
        click(personalAccountLink);
    }

    public void clickConstructorLink() {
        click(constructorLink);
    }

    public void clickLogo() {
        click(logoLink);
    }

    public boolean isConstructorLinkActive() {
        return constructorLink.getAttribute("class").contains("AppHeader_header__link_active");
    }

    public boolean isMainPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Соберите бургер']"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickBunsTab() {
        click(bunsTab);
        waitForBunsSectionVisible();
    }

    public void clickSaucesTab() {
        click(saucesTab);
        waitForSaucesSectionVisible();
    }

    public void clickFillingsTab() {
        click(fillingsTab);
        waitForFillingsSectionVisible();
    }

    public void waitForBunsSectionVisible() {
        wait.until(ExpectedConditions.visibilityOf(bunsHeader));
    }

    public void waitForSaucesSectionVisible() {
        wait.until(ExpectedConditions.visibilityOf(saucesHeader));
    }

    public void waitForFillingsSectionVisible() {
        wait.until(ExpectedConditions.visibilityOf(fillingsHeader));
    }

    public boolean isBunsSectionVisible() {
        try {
            return bunsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSaucesSectionVisible() {
        try {
            return saucesHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFillingsSectionVisible() {
        try {
            return fillingsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}