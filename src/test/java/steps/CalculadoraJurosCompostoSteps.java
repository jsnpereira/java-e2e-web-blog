package steps;

import drivers.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.CalculadoraJurosCompostoPage;

import java.util.List;
import java.util.Map;

public class CalculadoraJurosCompostoSteps {
    CalculadoraJurosCompostoPage calculadoraJurosCompostoPage;

    @Before
    public void setUp() {
        calculadoraJurosCompostoPage = new CalculadoraJurosCompostoPage(DriverFactory.getDriver());
    }

    @Given("a página da calculadora de juros compostos é exibida")
    public void verificarPaginaCalculadoraJurosComposto() {
        calculadoraJurosCompostoPage.verificarPaginaCalculadoraJurosComposto();
    }

    @And("clicar no botão investimento")
    public void clicarbotaoInvestimento(){
        calculadoraJurosCompostoPage.clicarbotaoInvestimento();
    }


    @And("preencher os campos do formulário com os seguintes dados:")
    public void preencherOsCamposDoFormulárioComOsSeguintesDados(DataTable data) {
        List<Map<String, String>> formData = data.asMaps(String.class, String.class);
        for (Map<String, String> row : formData) {
            calculadoraJurosCompostoPage.digitaValorInicial(row.get("valorInicial"));
            calculadoraJurosCompostoPage.digitaValorMensal(row.get("valorMensal"));
            calculadoraJurosCompostoPage.digitaTaxaJuros(row.get("taxaJuros"));
            calculadoraJurosCompostoPage.digitaPeriodo(row.get("tempoInvestimento"));
        }
    }

    @And("clicar o botão calcular o investimento")
    public void clicarOBotãoCalcularAgora() {
        calculadoraJurosCompostoPage.clicarCalcularAgora();
    }

    @Then("o resultado do cálculo de juros composto é exibido")
    public void oResultadoDoCálculoDeJurosCompostoÉExibido(DataTable data) {
        calculadoraJurosCompostoPage.resultadoCalculoExibido();

        List<Map<String, String>> formData = data.asMaps(String.class, String.class);
        for (Map<String, String> row : formData) {
            calculadoraJurosCompostoPage.verificarValorFinal(row.get("valorFinal"));
            calculadoraJurosCompostoPage.verificarValorInvestimento(row.get("valorInvestido"));
            calculadoraJurosCompostoPage.verificarRendimentoTotal(row.get("rendimentoTotal"));
        }
    }
}
