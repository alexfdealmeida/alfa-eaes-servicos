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
Se descobrir que o `angular-cli` não está instalado, execute, na linha de comando (com permissões
de administrador): `npm install -g @angular-cli`

Observação: existe um bug na versão 16.0.4 do Ubuntu que pode causar erros no processo de
instalação do `angular-cli` e execução do projeto Angular. Se perceber que isso está acontecendo
verifique os passos contidos [aqui](https://github.com/julianobrasil/servicos-pos-unialfa/issues/9).

### Instalando as dependências do projeto Angular

O Angular é um projeto `nodejs`. Como todo projeto `nodejs`, existe um arquivo chamando `package.json`
que traz as informações das dependências (biblitecas) necessárias para o projeto ser executado.
Esse arquivo é lido e interpretado pelo `npm` (_nodejs package manager_) É algo como o `Maven` no mundo 
java.

Para que o `npm` faça o download das dependências necessárias, da janela de um terminal: 
1. Tenha certeza de ter permissões de administrador na janeja do terminal, caso contrário não
conseguirá executar os comandos seugintes com sucesso.
2. **de dentro do diretório do projeto Angular**, apague o arquivo `package-lock.json`, se existir
3. Digite `npm install` (**tenha certeza de que esse terminal te dê a opção de executar os comandos como 
administrador**). 

Cerca de 100MB de dependências serão incorporados ao seu projeto Angular (portanto 
o download pode demorar um pouco: não faça isso usando a rede dados do seu celular).

### Executando o projeto Angular

O projeto Angular irá solicitar dados ao servidor, que serão enviados no formato json, usando o protocolo 
Http. Portanto, se o servidor não estiver ligado quando você iniciar o projeto, a tela não será operacional.
Assim: inicie primeiro o servidor.

Depois que o servidor estiver funcionando, inicie o pequeno servidor que vem no `angular-cli`, digitando
na linha de comando: `ng serve`.

Esse comando irá servir as páginas do projeto Angular na porta padrão 4200. Na janela de um navegador, digite:
http://localhost:4200.

## A Infraestrutura de Serviços

A infraestrutura de serviços está representada por alguns componentes do springcloud:

- Eureka
- Zuul
- Feign

Não existe a pretenção de exaurir as possibilidades de configuração existentes no Springcloud e nem tampouco
de apresentar todos os produtos nele contidos (razão pela qual, por exemplo, não está representado o Hystrix 
aqui).

A arquitetura que se almeja pode ser vista no gif animado a seguir:

![image](https://media.giphy.com/media/xT0xeNVbJQRBnxHfd6/giphy.gif)

**Observação:** por simplicidade, não existe aqui a inserção de funcionalidades de segurança nessa
arquitetura. Porém o cadeado na figura acima é um bom começo para se pensar em um projeto de segurança
integrado ao Springcloud, eventualmente com o uso do Spring Security Oauth2.

### A mensageria

No diretório [com-mensageria](com-mensageria/README.md) você pode encontrar a mesma estrutura com a adição de um serviço de mensageria
para manter os usuários dos clientes sincronizados.

## Pontos de interesse

- O conjunto não está completo: existe <a href="#atividades">atividade para você fazer</a>
- Observe que existem 2 serviços de usuário: isso faz parte do quesito "Confiabilidade"
- Como deve ter visto, o projeto é composto de 5 aplicativos springboot e um cliente angular:
    - Se for executá-los em um único computador, tenha certeza de que existe memória suficiente para
    que tudo não fique excessivamente lento
- Caso tenha interesse em testar a configuração nos servidores do [Heroku](www.heroku.com), verifique o conteúdo da
pasta "heroku" 

## <span id="atividades">Atividades</span>

### Atividade 1: implementar a exclusão de tarefa

Nosso cliente Angular está pronto para funcionar plenamente, mas ainda não temos um servidor com toda a API
implementada e este é o seu desafio. Veja como é a API RestFul:

#### USUÁRIOS

- http://localhost:8080/usuarios/usuarios/all: 
    - status: **funcionando**
    - verbo: GET
    - parâmetros: nenhum
    - retorno: retorna uma lista paginada com todos os usuários do sistema
- http://localhost:8080/usuarios/usuarios/save-or-create
    - status: **funcionando**
    - verbo: POST
    - o que faz: cria ou atualiza um usuário
    - parâmetro: um objeto usuário
    - retorno: retorna o usuário salvo
- http://localhost:8080/usuarios/usuarios/remove/{id do usuário para remover}
    - status: **funcionando**
    - verbo: DELETE
    - o que faz: remove o usuário indicado pelo id (na URL). 
    - parâmetro: id do usuário a ser removido (na URL)
     retorno: não retorna nada

### TAREFAS

- http://localhost:8080/tarefas/tarefas/all
    - status: **funcionando**
    - verbo: GET
    - parâmetros: nenhum
    - retorno: retorna uma lista paginada com todas as tarefas do sistema
- http://localhost:8080/tarefas/tarefas/save-or-create
    - status: **funcionando**
    - verbo: POST
    - o que faz: cria ou atualiza uma tarefa
    - parâmetro: um objeto tarefa
    - retorno: retorna a tarefa salva
- http://localhost:8080/tarefas/tarefas/remove/{id}
    - status: **implementada parcialmente**
    - verbo: DELETE
    - o que faz: remove a tarefa indicada pelo id (na URL). Para remover uma tarefa, é preciso, 
    antes, remover todas as alocações envolvidas (alocação de usuários na tarefa). É essa parte
    que não existe no servidor. Observe que você não precisa mexer no cliente para que esta
    função funcione corretamente. Basta implementar, no serviço apropriado, a <a href="#atividadeFaltante">API faltando abaixo</a>.
    - parâmetro: id da tarefa a ser removida (na URL)

### ALOCAÇÃO DE TAREFAS A USUÁRIOS

- http://localhost:8080/tarefas/usuarios-tarefas/find-by-tarefa-id/{idTarefa}
    - status: **funcionando**
    - verbo: GET
    - parâmetros: id de uma tarefa
    - retorno: retorna uma lista paginada com todos os objetos UsuarioTarefa
    relacionados à tarefa cujo id foi informado na URL.
- http://localhost:8080/tarefas/usuarios-tarefas/save-or-create
    - status: **funcionando**
    - verbo: POST
    - o que faz: cria ou atualiza um objeto UsuarioTarefa
    - parâmetro: um objeto UsuarioTarefa
    - retorno: retorna o UsuarioTarefa salvo
- http://localhost:8080/tarefas/usuarios-tarefas/remove/{id}
    - status: **funcionando**
    - verbo: DELETE
    - o que faz: remove um registro do tipo UsuarioTarefa, indicado pelo id (na URL). 
    - parâmetro: id do UsuarioTarefa a ser removido (na URL)
- http://localhost:8080/tarefas/usuarios-tarefas/remove-tarefas-do-usuario/{usuarioId}
    - status: **funcionando**
    - verbo: DELETE
    - o que faz: remove todos os registros UsuarioTarefa ligados a um usuário, indicado pelo id (na URL). 
    - parâmetro: id do Usuario cujas tarefas devem ser removidas (na URL)
    - retorno: retorna true se a remoção for feita com sucesso
- <span id="atividadeFaltante">http://localhost:8080/tarefas/usuarios-tarefas/remove-usuarios-da-tarefa/{tarefaId}</span>
    - status: **não implementada**
    - verbo: DELETE
    - o que faz: remove todos os registros UsuarioTarefa ligados a uma tarefa, indicada pelo id (na URL). 
    - parâmetro: id da Tarefa cujos usuários devem ser removidos (na URL)
    - retorno: retorna true se a remoção for feita com sucesso
    
### Atividade 2: Nível 3 do Modelo de Maturidade de Richardson (grupos de 2 pessoas)

- Pré-requisito: um dos componentes do grupo precisa ter algum conhecimento de Angular

Escolha um dos "serviços" da atividade 1 e implemente o nível 3 de maturidade de Richardson
em todos os serviços

