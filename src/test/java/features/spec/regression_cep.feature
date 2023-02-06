Feature: Consultar CEP via CEP
  Como usuário
  Eu quero consultar o endereço através do CEP informado
  Para que eu possa saber o endereço correspondente ao CEP informado

  @run
  Scenario Outline: Consultar endereço pelo CEP
    Given que eu informei o CEP <cep>
    When eu solicito a consulta do endereço
    Then eu recebo o endereço cep <cep>, rua <rua>, complemento <complemento>, bairro <bairro>, cidade <cidade>, estado <estado> e frete <frete>
    Examples:
      | cep       | rua         | complemento  | bairro   | cidade     |  estado  | frete    |
      | 01001000  | Praça da Sé | lado ímpar   | Sé       | São Paulo  | SP       |  7,85    |


  @run
  Scenario Outline: Consultar endereço pelo CEP, porém CEP está no formato incorreto.
    Given que eu informei o CEP <cep>
    When eu solicito a consulta do endereço
    Then a mensagem de erro correta é gerado
    Examples:
      | cep       |
      | 01001000  |