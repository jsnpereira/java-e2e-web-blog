package steps;

import drivers.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class HomePageSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
    }

    @Given("estou na página inicial do Agibank")
    public void acessarHomePage() {
        homePage.acessarHomePage();
    }

    @And("o título da página inicial é exibido")
    public void homePageTituloExibido() {
        homePage.homePageTituloExibido();
    }

    @And("seguir o menu para abrir a página calculadora de juros compostos")
    public void clicarMenuCalculadoras() {
        homePage.passaSobreMenuCalculadoras();
        homePage.clicarSubmenuJurosComposto();
    }


}
