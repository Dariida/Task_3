package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {

    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement logoutButton;

    public AccountPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlContains("/account"));
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public boolean isAccountPageDisplayed() {
        try {
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}