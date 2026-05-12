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

    public MainPage(WebDriver driver) {
        super(driver);
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
        wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current"));
    }

    public void clickSaucesTab() {
        click(saucesTab);
        wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current"));
    }

    public void clickFillingsTab() {
        click(fillingsTab);
        wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current"));
    }

    public boolean isBunsTabActive() {
        return bunsTab.getAttribute("class").contains("tab_tab_type_current");
    }

    public boolean isSaucesTabActive() {
        return saucesTab.getAttribute("class").contains("tab_tab_type_current");
    }

    public boolean isFillingsTabActive() {
        return fillingsTab.getAttribute("class").contains("tab_tab_type_current");
    }
}