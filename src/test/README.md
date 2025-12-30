Testes Automatizados – Desafio Board (DIO)

Este diretório contém os testes automatizados desenvolvidos para o projeto Desafio Board – DIO, com foco em testes unitários, validação de regras de negócio e garantia de qualidade do código.

Os testes foram implementados utilizando JUnit 5, integrados ao Maven, e executados com sucesso via comando mvn test.

🎯 Objetivos dos Testes

Garantir o correto funcionamento das entidades de domínio

Validar regras básicas de negócio

Detectar erros de compilação e inconsistências no domínio

Assegurar que o projeto esteja buildável e testável

Demonstrar boas práticas em Quality Assurance (QA) e Testes Unitários

🛠️ Tecnologias Utilizadas

Java 17

JUnit 5 (Jupiter)

Maven

Surefire Plugin

IDE IntelliJ IDEA

Git / GitHub

📂 Estrutura de Testes
src
└── test
└── java
└── br
└── com
└── dio
└── board
├── domain
│   ├── BoardTest.java
│   └── ColumnTypeTest.java
└── service
└── BoardServiceTest.java

✅ Testes Implementados
🔹 BoardTest

Responsável por validar a criação da entidade Board.

Cenários testados:

Criação de um board com ID e nome válidos

Validação dos valores retornados pelos métodos de acesso (id() e name())

🔹 ColumnTypeTest

Responsável por validar o comportamento do enum ColumnType.

Cenários testados:

Verificação dos tipos de coluna disponíveis

Validação da ordem e existência dos valores esperados

Garantia de alinhamento entre domínio e testes

📌 Importante:
Durante o processo de testes, foi identificado um desalinhamento entre os valores esperados no teste e o enum real, o que exigiu ajuste no código de domínio — evidenciando a importância dos testes automatizados.

🔹 BoardServiceTest

Responsável por validar o comportamento da camada de serviço.

Cenários testados:

Criação de um board via BoardService

Verificação do retorno esperado

Garantia de funcionamento correto da regra de negócio

▶️ Como Executar os Testes

Na raiz do projeto, execute:

mvn test

Resultado esperado:

Todos os testes executados com sucesso

Build finalizado sem erros

Exemplo de saída:

Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS

🧠 Boas Práticas Aplicadas

Testes unitários isolados

Organização por camada (domain / service)

Nomes de testes claros e descritivos

Uso de assertions do JUnit 5

Integração total com o ciclo de build Maven

📌 Considerações Finais

Este conjunto de testes demonstra:

Conhecimento em JUnit 5

Capacidade de identificar e corrigir falhas no domínio

Uso prático de testes como ferramenta de validação

Compromisso com qualidade de software

Os testes garantem que o projeto esteja estável, confiável e pronto para evolução futura.

👨‍💻 Autor

Luiz Carvalho
Bootcamp DIO – Desenvolvimento Backend Java
Foco em Qualidade de Software, Testes Automatizados e Backend