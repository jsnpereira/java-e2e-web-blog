package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.DriverManager;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.silentOutput", "true");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void fecharDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
