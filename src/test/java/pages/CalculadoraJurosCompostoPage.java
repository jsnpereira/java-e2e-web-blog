package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CalculadoraJurosCompostoPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By iframe = By.tagName("iframe");
    private By tituloPagina = By.id("calculadora-de-juros-compostos");
    private By investimentoButaoCard = By.xpath("//div[contains(@class,'investimento')]");
    private By valorInicialInput = By.id("valorInicial");
    private By valorMensalInput = By.id("valorMensal");
    private By taxaJurosInput = By.id("taxaJurosInvest");
    private By periodoInput = By.id("periodoInvest");
    private By calcularAgorabutton = By.xpath("//*[@id='formInvestimento']/button");
    private By valorFinalLabel = By.id("valorFinal");
    private By valorInvestidoLabel = By.id("valorInvestido");
    private By rendimentoTotalLabel = By.id("rendimentoTotal");



    public CalculadoraJurosCompostoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void verificarPaginaCalculadoraJurosComposto() {
        descerPaginaParaElement(tituloPagina);
    }

    public void clicarbotaoInvestimento() {
        entrarIframe();
        WebElement element = driver.findElement(investimentoButaoCard);
        wait.until(driver -> element.isDisplayed());
        element.click();
        sairIframe();
    }

    public void digitaValorInicial(String valorInicial){
        entrarIframe();
        WebElement element = driver.findElement(valorInicialInput);
        wait.until(driver -> element.isDisplayed());
        element.clear();
        element.sendKeys(valorInicial);
        sairIframe();
    }

    public void digitaValorMensal(String valorMensal){
        entrarIframe();
        WebElement element = driver.findElement(valorMensalInput);
        wait.until(driver -> element.isDisplayed());
        element.clear();
        element.sendKeys(valorMensal);
        sairIframe();
    }

    public void digitaTaxaJuros(String taxaJuros){
        entrarIframe();
        WebElement element = driver.findElement(taxaJurosInput);
        wait.until(driver -> element.isDisplayed());
        element.clear();
        element.sendKeys(taxaJuros);
        sairIframe();
    }

    public void digitaPeriodo(String periodo){
        entrarIframe();
        WebElement element = driver.findElement(periodoInput);
        wait.until(driver -> element.isDisplayed());
        element.clear();
        element.sendKeys(periodo);
        sairIframe();
    }

    public void clicarCalcularAgora() {
        entrarIframe();
        WebElement element = driver.findElement(calcularAgorabutton);
        wait.until(driver -> element.isDisplayed());
        element.click();
        sairIframe();
    }

    public void resultadoCalculoExibido() {
        entrarIframe();
        WebElement element = driver.findElement(By.id("resultInvestimento"));
        wait.until(driver -> element.isDisplayed());
        sairIframe();
    }

    public void verificarValorFinal(String valor){
        entrarIframe();
        WebElement element = driver.findElement(valorFinalLabel);
        wait.until(driver -> element.isDisplayed());
        Assert.assertEquals(element.getText().replace("R$ ", ""),valor);
        sairIframe();
    }

    public void verificarValorInvestimento(String valor){
        entrarIframe();
        WebElement element = driver.findElement(valorInvestidoLabel);
        wait.until(driver -> element.isDisplayed());
        Assert.assertEquals(element.getText().replace("R$ ", ""), valor);
        sairIframe();
    }

    public void verificarRendimentoTotal(String valor){
        entrarIframe();
        WebElement element = driver.findElement(rendimentoTotalLabel);
        wait.until(driver -> element.isDisplayed());
        Assert.assertEquals(element.getText().replace("R$ ", ""), valor);
        sairIframe();
    }

    private void entrarIframe(){
        WebElement element = driver.findElement(iframe);
        wait.until(driver -> element.isDisplayed());
        driver.switchTo().frame(element);
    }

    private void sairIframe(){
        driver.switchTo().defaultContent();
    }

    private void descerPaginaParaElement(By locator) {
        WebElement element = wait.until(driver -> driver.findElement(locator));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
