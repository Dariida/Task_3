package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                return new ChromeDriver(options);
            case "yandex":
                // Указываем версию ChromeDriver, совместимую с браузером 146.0.7680.1026
                WebDriverManager.chromedriver().driverVersion("146.0.7680.0").setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.addArguments("--remote-allow-origins=*");
                yandexOptions.setBinary("C:/Program Files/Yandex/YandexBrowser/Application/browser.exe");
                return new ChromeDriver(yandexOptions);
            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browserName);
        }
    }
}