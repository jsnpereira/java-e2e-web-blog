package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String ambiente = System.getProperty("ambiente","local");
            if(ambiente.equals("ci")){
                try {
                    driver = setupRemoteDriver();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                driver = setupDriverLocal();
            }

            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver setupDriverLocal(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver(options);
        return driver;
    }

    private static WebDriver setupRemoteDriver() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bsOptions = new HashMap<>();

        bsOptions.put("userName", System.getenv("BROWSERSTACK_USERNAME"));
        bsOptions.put("accessKey", System.getenv("BROWSERSTACK_ACCESS_KEY"));
        bsOptions.put("buildName", "Blog Agibank E2E");
        bsOptions.put("sessionName", "Calculadora Juros Compostos");

        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("bstack:options", bsOptions);

        return new RemoteWebDriver(
                new URL("https://hub.browserstack.com/wd/hub"),
                capabilities
        );
    }

    public static void fecharDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
