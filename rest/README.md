## Executando o projeto Angular

### O `angular-cli`

Antes de mais nada, tenha certeza de que o [angular-cli](https://cli.angular.io/) está instalado. Ele é a _command line
interface_ oficial do Google para o Angular.

Para verificar, execute `ng -v` na linha de comando de um terminal. Você 
deverá ver algo assim:

```
$ ng -v
_                      _                 ____ _     ___
/ \   _ __   __ _ _   _| | __ _ _ __     / ___| |   |_ _|
/ △ \ | '_ \ / _` | | | | |/ _` | '__|   | |   | |    | |
/ ___ \| | | | (_| | |_| | | (_| | |      | |___| |___ | |
/_/   \_\_| |_|\__, |\__,_|_|\__,_|_|       \____|_____|___|
|___/

Angular CLI: 1.5.3
Node: 8.9.1
OS: win32 x64
Angular:
...

```
Se descobrir que o `angular-cli` não está instalado, execute, na linha de comando: 
`npm install -g angular-cli`

Observação: existe um bug na versão 16.0.4 do Ubuntu que pode causar erros no processo de
instalação do `angular-cli` e execução do projeto Angular. Se perceber que isso está acontecendo
verifique os passos contidos [aqui](https://github.com/julianobrasil/servicos-pos-unialfa/issues/9).

### Instalando as dependências do projeto Angular

O Angular é um projeto `nodejs`. Como todo projeto `nodejs`, existe um arquivo chamando `package.json`
que traz as informações das dependências (biblitecas) necessárias para o projeto ser executado.
Esse arquivo é lido e interpretado pelo `npm` (_nodejs package manager_) É algo como o `Maven` no mundo 
java.

Para que o `npm` faça o download das dependências necessárias: **de dentro do diretório do projeto 
Angular**, digite `npm install` na janela de um terminal. Cerca de 100MB de dependências serão incorporados
ao seu projeto Angular (portanto o download pode demorar um pouco: não faça isso usando a rede dados do
seu celular).

### Executando o projeto Angular

O projeto Angular irá solicitar dados ao servidor, que serão enviados no formato json, usando o protocolo 
Http. Portanto, se o servidor não estiver ligado quando você iniciar o projeto, a tela não será operacional.
Assim: inicie primeiro o servidor.

Depois que o servidor estiver funcionando, inicie o pequeno servidor que vem no `angular-cli`, digitando
na linha de comando: `ng serve`.

Esse comando irá servir as páginas do projeto Angular na porta padrão 4200. Na janela de um navegador, digite:
http://localhost:4200.

## Limitações do projeto

Você irá perceber que somente a aba "Usuário" está funcionando. Isso é proposital, para que você desenvolva
o restante da comunicação necessária para as outras abas funcionarem (no projeto do servidor).

## Não temos um serviço ainda

Apesar de termos uma aplicação distribuída usando comunicação RestFul, ainda não temos um serviço, pelas razões
que, se você já assistiu à aula, já deve saber bem

## Atividades

### Atividade 1: implementar o restante da API Restful (grupos de 2 pessoas)

Nosso cliente Angular está pronto para funcionar plenamente, mas... espere! Não temos um servidor com toda a API
implementada e este é o seu desafio. Veja como é a API RestFul:

#### USUÁRIOS

- http://localhost:9000/usuarios/all: 
    - status: **funcionando**
    - verbo: GET
    - parâmetros: nenhum
    - retorno: retorna uma lista paginada com todos os usuários do sistema
- http://localhost:9000/usuarios/save-or-create
    - status: **funcionando**
    - verbo: POST
    - o que faz: cria ou atualiza um usuário
    - parâmetro: um objeto usuário
    - retorno: retorna o usuário salvo
- http://localhost:9000/usuarios/remove/{id do usuário para remover}
    - status: **funcionando**
    - verbo: DELETE
    - o que faz: remove o usuário indicado pelo id (na URL). 
    - parâmetro: id do usuário a ser removido (na URL)
     retorno: não retorna nada

### TAREFAS

- http://localhost:9000/tarefas/all
    - status: **não implementada**
    - verbo: GET
    - parâmetros: nenhum
    - retorno: retorna uma lista paginada com todas as tarefas do sistema
- http://localhost:9000/tarefas/save-or-create
    - status: **não implementada**
    - verbo: POST
    - o que faz: cria ou atualiza uma tarefa
    - parâmetro: um objeto tarefa
    - retorno: retorna a tarefa salva
- http://localhost:9000/tarefas/remove/{id}
    - status: **não implementada**
    - verbo: DELETE
    - o que faz: remove a tarefa indicada pelo id (na URL). 
    - parâmetro: id da tarefa a ser removida (na URL)

### ALOCAÇÃO DE TAREFAS A USUÁRIOS

- http://localhost:9000/usuarios-tarefas/find-by-tarefa-id/{idTarefa}
    - status: **não implementada**
    - verbo: GET
    - parâmetros: id de uma tarefa
    - retorno: retorna uma lista paginada com todos os objetos UsuarioTarefa
    relacionados à tarefa cujo id foi informado na URL.
- http://localhost:9000/usuarios-tarefas/save-or-create
    - status: **não implementada**
    - verbo: POST
    - o que faz: cria ou atualiza um objeto UsuarioTarefa
    - parâmetro: um objeto UsuarioTarefa
    - retorno: retorna o UsuarioTarefa salvo
- http://localhost:9000/usuarios-tarefas/remove/{id}
    - status: **não implementada**
    - verbo: DELETE
    - o que faz: remove um registro do tipo UsuarioTArefa, indicado pelo id (na URL). 
    - parâmetro: id do UsuarioTarefa a ser removido (na URL)
    
### Atividade 2: Nível 3 do Modelo de Maturidade de Richardson (grupos de 2 pessoas)

- Pré-requisito: um dos componentes do grupo precisa ter algum conhecimento de Angular

Escolha um dos "serviços" da atividade 1 e implemente o nível 3 de maturidade de Richardson
em todo o serviço

