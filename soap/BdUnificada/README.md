# Base de Dados Unificada

Existem 2 projetos nesta área:

- Sistema monolítico modificado
    - O SPA foi aqui modificado para solicitar o usuário usando o seu id por meio de um serviço.
    Essa requisição é feita quando o cliente clica no icone do lapis para editar o usuario.
- Serviço de Usuários
    - Foram disponibilizados 2 serviços no projeto:
        - **Um para demonstração**: obtém usuários por parte do nome. Este serviço não está sendo 
	usado no cliente. Ele apenas solicita todos os usuários cujo nome possuem o token
	"an" e os escreve no console
	    - **Outro para uso do cliente**: obtém um usuário por id

A abordagem do SOAP usada aqui foi a _Contract First_ ("O contrato é mais importante")

## Estrutura do Projetos

### SPA

- Referência: [Documentação do Spring](https://spring.io/guides/gs/consuming-web-service/)
- Banco de Dados: MySql
    - O Banco é gerado inteiramente pela aplicação. Para isso é preciso que exista, no banco, 
	um usuário "pos", com a senha 123456, com permissão suficiente para realizar operações de 
	CRUD, assim como criar, apagar e modificar bases de dados
- Framework do projeto: Spring
    - Usamos aqui o framework Spring, em sua distribuição já empacotada: Springboot + JPA
- Framework de Templates HTML: Thymeleaf 3
    - Usamos o Thymeleaf para gerar a página que será carregada
- Framework CSS: Bootstrap 3.7.1
- Framework de segurança: Por simplicidade do projeto não está sendo usado nenhum framework para
a parte de segurança, como o Spring Security.

### Serviço de Usuários

- Referência: [Documentação do Spring](https://spring.io/guides/gs/producing-web-service/)
- Banco de Dados: MySql
    - O Banco é gerado inteiramente pela aplicação. Para isso é preciso que exista, no banco, 
	um usuário "pos", com a senha 123456, com permissão suficiente para realizar operações de 
	CRUD, assim como criar, apagar e modificar bases de dados
- Framework do projeto: Spring
    - Usamos aqui o framework Spring, em sua distribuição já empacotada: Springboot + JPA
- Framework de segurança: Por simplicidade do projeto não está sendo usado nenhum framework para
a parte de segurança, como o Spring Security.
