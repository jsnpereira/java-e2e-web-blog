Feature: Campo de pesquisa

  @pesquisa
  Scenario: Digitar o pix no campo de pesquisa
    Given estou na página inicial do Agibank
    And clicar no botão da pequisa
    When Digitar o pix no campo da pesquisa
    Then o resultado retorno a lista sobre o pix