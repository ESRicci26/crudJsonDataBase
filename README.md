Este código fornece um exemplo básico de um CRUD em Java Swing com persistência em JSON e geração de PDF.
Você pode estender esse exemplo adicionando validações de entrada, tratamento de exceções mais robusto, e outras funcionalidades conforme necessário.
Para que o código funcione corretamente, certifique-se de adicionar as bibliotecas json-simple e iText ao seu projeto, quando criei o projeto adicionei
json-simple-1.1.1.jar e itextpdf-5.5.13.3.jar, se estiver usando um sistema de build como Maven, adicione as dependências ao seu pom.xml.

Estrutura do Projeto:
Nosso projeto tem as seguintes classes:

1) Funcionario: Representa um funcionário.
2) FuncionarioDAO: Manipula o arquivo JSON para CRUD.
3) MainFrame: A interface gráfica com o usuário.
4) PDFGenerator: Gera o arquivo PDF.

o programa cria o diretório data e o arquivo funcionarios.json dentro da estrutura do projeto se eles não existirem, evitando o erro de FileNotFoundException.
Certifique-se de que o projeto tenha permissões para criar diretórios e arquivos no sistema de arquivos.
