# API REST que permite realizar cadastro de uma pessoa preenchendo automaticamente o tipo de identificador (CPF ou CNPJ)

Projeto desenvolvido com Spring para atender à um desafio técnico

- Implementa rota para cadastrar uma pessoa em um banco de dados em memória H2
- Testes de integração

## Executando aplicação

Navegar até a raiz do projeto via linha de comando e executar o seguinte comando: mvn spring-boot:run

## Executando Testes

Navegar até raiz do projeto via linha de comando e executar o seguinte comando: mvn test

## Exemplos de Chamadas

Nesta seção são expostas as instruções para realização de cada uma das possíveis chamadas:

#### Cadastro de pessoa

http://localhost:8080/pessoas
{
    "nome": "Yuri Stapassoli de Sá",
    "identificador": "05590050910"
}
