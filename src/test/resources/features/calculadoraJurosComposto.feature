Feature:
    Cálculo de juros composto

    Background:
        Given estou na página inicial do Agibank
        And o título da página inicial é exibido

    Scenario: Calcular o valor final em investimento
        Given seguir o menu para abrir a página calculadora de juros compostos
        And a página da calculadora de juros compostos é exibida
        When clicar no botão investimento
        And  preencher os campos do formulário com os seguintes dados:
          | valorInicial | valorMensal | taxaJuros | tempoInvestimento |
          | 1000,00      | 500,00      | 0,80      | 12                |
        And clicar o botão calcular o investimento
        Then o resultado do cálculo de juros composto é exibido
          |valorFinal  | valorInvestido | rendimentoTotal |
          | 7.371,51   | 7.000,00       | 371,51          |

