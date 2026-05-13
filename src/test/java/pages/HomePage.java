package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By homePageTitle = By.xpath("//*[contains(@class,'menu-text') and contains(text(),'Agibank')]");
    private By calculadorasMenu = By.xpath("//span[text()=\"Calculadoras\"]");
    private By jurosCompostoSubmenu = By.xpath("//a[@class=\"menu-link\"]//span[text()=\"Calculadora de Juros Compostos\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.actions = new Actions(driver);
    }

    public void acessarHomePage() {
        driver.get("https://blog.agibank.com.br/");
        loading();
    }

    public void homePageTituloExibido() {
        wait.until(ExpectedConditions.presenceOfElementLocated(homePageTitle));
    }

    public void passaSobreMenuCalculadoras () {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(calculadorasMenu));
        String ambiente = System.getProperty("ambiente","local");
        actions.moveToElement(element).perform();
    }

    public void clicarSubmenuJurosComposto () {
        wait.until(ExpectedConditions.presenceOfElementLocated(jurosCompostoSubmenu));
        driver.findElement(jurosCompostoSubmenu).click();
    }

    public void loading() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                String status = ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").toString();
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(pageLoadCondition);
    }
}
