# Single Page Application

Nesta parte temos uma Single Page Application (SPA) que será usada em todos os projetos para
exemplificar o uso de serviços.

Aqui ela foi construída como um sistema web monolítico, com a renderização das páginas feita
inteiramente no servidor

## Telas da aplicação

### Usuários

![image](https://media.giphy.com/media/3ohs82kLpllDktGn1C/giphy.gif)

### Tarefas

![image](https://media.giphy.com/media/3o6fIVWlrGtjU15wm4/giphy.gif)

### Alocação de usuários a tarefas

![image](https://media.giphy.com/media/3osBLvNoKQ3bzfgzBK/giphy.gif)

## Estrutura do projeto

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

## Estrutura da base de dados

A base de dados é extremamente simples, sendo composta pelas seguintes relações:

- tarefa
- usuario
- usuario_tarefa
    - Estabelece o relacionamento entre usuario e tarefa (muitos para muitos): um usuário pode ter
	muitas tarefas e uma tarefa pode ser executada/acompanhada por vários usuários
- comentario
    - Um comentário possui um autor (usuario) e está diretamente ligado a uma e somente uma tarefa
	
## Executando a aplicação

A aplicação está sendo executada na porta 9001. Portanto, uma vez em execução, para testá-la, abra o
navegador e acesse http://localhost:9001/spa.

## Atividade proposta

- Nenhuma
