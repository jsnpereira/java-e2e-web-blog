package steps;

import drivers.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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


    @And("clicar no botão da pequisa")
    public void clicarNoBotaoDaPequisa() {
        homePage.clicarSearchButton();
    }

    @When("Digitar o pix no campo da pesquisa")
    public void digitarOPixNoCampoDaPesquisa() {
        homePage.digitarSearchInput("pix");
    }

    @Then("o resultado retorno a lista sobre o pix")
    public void oResultadoRetornoAListaSobreOPix() {
        homePage.validarPrimeiroItemDaLista("Pix");
    }
}
