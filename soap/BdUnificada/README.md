# Base de Dados Unificada

Existem 2 projetos nesta área:

- Sistema monolítico modificado
- Serviço de Usuários

## Estrutura do Projetos

### SPA

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
