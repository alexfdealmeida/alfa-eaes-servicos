## O Heroku.com

O heroku.com é uma empresa que vende "plataforma como serviço". Resumidamente, você pode usar
o serviço do heroku para executar as suas aplicações. Ele dá suporte a diversas plataformas e
bancos de dados. Esse procedimento é feito usando o Heroku como um repositório GIT (basicamente
você coloca o seu projeto lá e ele compila o projeto e o coloca em execução).

### O serviço gratuito do heroku

É possível ter até 5 aplicações, executando com recursos limitados, gratuitamente no heroku.
O plano gratuito do heroku.com oferece ainda o banco de dados PostgreSQL (~7MB). Resumindo,
a versão gratuita dele é exclusivamente para testes.

### Usando o heroku

Hospedar uma aplicação feita no springboot no heroku é um processo bem transparente, não exigindo
nenhuma configuração especial (alguns apps precisam de algumas pequenas configurações, mas, no caso
dos apps feitos no springboot basta colocar a aplicação lá).

Você não irá precisar mudar o seu projeto do Github para o heroku. Apesar dele usar os serviços do
Git para interagir com os seus apps, ele não tem a pretenção de ser um repositório para controlar
versões. O que você irá fazer é somente criar um atalho do seu repositório Git do seu computador
para o heroku.

O processo é bem simples e é explicado com detalhes da extensa documentação do Heroku.

Resumidamente, os passos são os seguintes:

##### 1. Você precisará instalar o [_command line interface_ (CLI) do heroku](https://devcenter.heroku.com/articles/heroku-cli).
##### 2. Uma vez instalado no seu computador, você executará: `heroku login` para poder se logar na sua conta
remota do heroku
##### 3. Depois de executado o passo 2, basta criar o seu primeiro app (vazio) na sua conta remota
executando, na linha de comando: `heroku create`
    - Você irá criar um app vazio no heroku, capaz de hospedar alguma aplicação
    - Toda vez que executar o comando `heroku create` você irá criar um app vazio no heroku, até o limite 
    de 5 se estiver usando uma conta gratuita.
    - Se estiver usando mais de uma conta do heroku para criar seus apps, terá que executar o comando
    `heroku login` para trocar de uma conta para outra. Isso é importante, porque nosso sistema aqui está
    distribuído em duas contas do heroku. Para usar o git para fazer upload de uma qualquer coisa para o
    heroku pela linha de comando usando o git, você precisa estar logado no heroku, na conta de interesse.
        - Note que, no Windows, os comandos do heroku não funcionam de dentro da tela de Git Bash (caso você 
        a esteja usando, precisará abrir um terminal do windos par executar os comandos `heroku login` e 
        `heroku create`).
##### 4. Agora você pode visitar o seu app vazio no site do heroku. Você deverá ver algo parecido com:

![image](https://media.giphy.com/media/3oxHQDt8XvGzuMCboQ/giphy.gif)

##### 5. O heroku terá criado um app vazio para você com um nome aleatório, que você pode mudar:

![image](https://media.giphy.com/media/l3mZksVzJ8pRbeG1W/giphy.gif)

Sugestão, coloque aqui um nome que seja algo intuitivo. Se for um app web, você poderá acessá-lo
depois usando https://nome-que-voce-escolheu.herokuapp.com. Uma coisa interessante é que o acesso será
pela porta 80, o que facilita a construção de serviços que serão acessados diretamente pelo app dos usuários
finais (visto que, em muitas empresas, as portas diferentes da porta 80 são bloqueadas por medida
de segurança).

##### 6. Feito isso, você precisará de um banco de dados do heroku (o gratuito, para testes apenas):

![image](https://media.giphy.com/media/3oxHQepTPgiipUFfK8/giphy.gif)

O banco possui apenas 7MB e pode ser acessado somente pelo app a que ele está ligado (você precisará ligar
o banco a um app => mesmo que você tente acessar a base por outro app, o heroku não irá deixar: irá criar
uma outra base de dados para ao outro app. Assim, na conta gratuita, não é possível compartilhar bases
de dados no herokuapp (a não ser que você crie um serviço para isso... porque não tenta?)

##### 7. Tendo configurado sua app vazia pelo site do heroku é hora de configurar o seu app springboot

As configurações no springboot resumem-se a (application.yml na pasta resources):

- Configurar o springdata para usar o banco:
    - Usar o PostgreSQL ao invés do MySql
    - Configurar os dados de acesso ao banco
- Configurar os clientes do Eureka para estarem na Internet usando https

OBSERVAÇÃO: Devido a um bug do Springcloud, na época da escrita deste documento, fui obrigado a fazer
um _downgrade_ da versão usada no projeto local (pom.xml).

Essas configurações acima podem ser vistas no gif abaixo:

Eureka (observe o nome do aplicativo escolhido no site do heroku):

![image](https://media.giphy.com/media/3oxHQGwY3mBtnYaSo8/giphy.gif)

Zuul (observe a referência ao Eureka - defaultZone):

![image](https://media.giphy.com/media/l3mZjiHuLQd3xTNJu/giphy.gif)

Serviços:

![image](https://media.giphy.com/media/3oxHQLyCI11UbQBNVS/giphy.gif)

Algumas das configurações acima dizem respeito ao uso:

- do https;
- do banco;
- de aplicações estarem em redes diferentes (hostname).

### Limitação importante do plano gratuito do heroku

Após 30 minutos sem uso, o app hospedado na conta grauita do heroku é colocado para dormir.
Por isso, no primeiro acesso após esses 30 minutos o app será acordado, o que pode levar um
tempo.

Essa informação é especialmente importante no caso de um serviço de registro/descoberta 
(Eureka), pois, para peramanecer no registro do Eureka, os clientes precisam atualizar
suas informações periodicamente. Após algum período sem receber atualização, o Eureka
retira o cliente do seu registro. Nesse caso, o que acontece:

1. Após 30 minutos sem uso, o Zuul vai dormir e deixa de atualizar suas informações junto ao
Eureka
2. Após o Zuul, não demora muito para que os serviços, sem serem acionados, também durmam
3. Sem ser acionado por nenhum serviço e também pelo Zuul, logo após o último serviço ter ido
dormir, o próprio Eureka também vai dormir.

Com todo o sistema dormindo, é impossível acordá-lo apenas fazendo uma requisição ao Zuul:

1. Você tenta usar o sistema e ele "acorda" o Zuul.
2. O Zuul precisa de informações do Eureka e "acorda" o Eureka
3. O Eureka não é um serviço ativo, ou seja, ele não va atrás dos clientes, mas espera que os
clientes se registrem nele. Portanto ele não sabe como encontrar os clientes e nenhum serviço
mais é acordado.

Nesse momento, a única alternativa que te sobra é ir ao heroku e manualmente reiniciar cada um
dos serviços, como mostrado abaixo (o processo abaixo tem que ser repetido para todos os serviços):

![image](https://media.giphy.com/media/l3mZaLKapUVMZu9bi/giphy.gif)

Não é necessário fazer o processo acima para o Eureka e nem para o Zuul, visto que:

1. Quando o primeiro serviço acordar, tentará se registrar no Eureka, acordando-o
2. Quando a aplicação tentar acessar o Zuul, ela o acordará e ele automaticamente irá até o Eureka
para pegar informações sobre os demais serviços (nesse momento pode ser que o seu primeiro acesso
falhe por estouro de temporização, mas após alguns segundos tudo deve ser normalizar)

## Configurando a sua aplicação no Heroku

Depois de criado o app vazio no heroku (um para cada aplicação que deseja executar) e renomeado, 
você irá fazer o _commit_ do seu repositorio git para ele (pelo menos da parte que interessa).

Por exemplo, na pasta `service-tarefas` está a aplicação de serviço de tarefas. Vamos ver como fazer
para colocá-la no app vazio do heroku (chamado _dyno_) onde ela será executada. Supondo que o 
_dyno_ esteja criado e que já tenha sido renomeado para **`servico-tarefas-unialfa`**.

Para executar os comandos abaixo, tenha certeza de ter executado o comando `heroku login` na conta
do heroku onde deseja hospedar a aplicação.

Na raiz do repositório local (não no diretório `service-tarefas`, mas em `servicos-pos-unialfa`), digite:

```
heroku git:remote -a servico-tarefas-unialfa
git remote rename heroku heroku-servico-tarefas
```

A primeira linha é um comando do Heroku CLI que irá criar um link entre o seu app vazio remoto no heroku e 
o seu repositório Git local. Esse link será chamado automaticamente de heroku (é padrão do heroku cli).

Como você vai fazer esse processo para cada uma das aplicações que deseja hospedar, então renomearemos esse
link para outro que lembre a que ele veio: será chamado de heroku-servico-tarefas. Isso é feito pela segunda 
linha.

Depois disso, você repetirá o processo para cada um dos apps que deseja rodar no heroku.

Se digitar `git remote -v` você poderá ver o resultado do seu trabalho e deverá ser algo assim:

![image](https://media.giphy.com/media/l3mZdHOQ15WhTeCDm/giphy.gif)

Quando terminar de criar o links do Git para os apps do heroku, irá fazer o commit do projeto de interesse
para a área do heroku. Por exemplo, para fazer isso com o serviço de tarefas, cujo link foi chamado de
`heroku-servico-tarefas`:

```
git subtree push --prefix infraestrutura/heroku/service-tarefas heroku-servico-tarefas master
```

Esse comando do git coloca somente o conteúdo da pasta service-tarefas no repositório hemoto do heroku,
no ramo master. É isso que o heroku precisa para compilar e executar o seu projeto.

Você precisará fazer isso com todos os projetos:

- Conta 1 do heroku:
    - eureka: git subtree push --prefix infraestrutura/heroku/eureka heroku-eureka master
    - zuul: git subtree push --prefix infraestrutura/heroku/eureka heroku-zuul master
    - service-tarefas: git subtree push --prefix infraestrutura/heroku/service-tarefas heroku-servico-tarefas master

- Conta 2 do heroku:
    - service-usuarios-1: git subtree push --prefix infraestrutura/heroku/service-usuarios-1 heroku-servico-usuarios-1 master
    - service-usuarios-2: git subtree push --prefix infraestrutura/heroku/service-usuarios-2 heroku-servico-usuarios-2 master
    - mensageria: git subtree push --prefix infraestrutura/heroku/mensageria heroku-servico-mensageria master

### Quando o seu commit no heroku for rejeitado

Eventualmente, você fará modificações no seu ramo local que o Git não conseguirá fundir no ramo remoto no heroku. 
Nesse momento, você poderá usar um outro artifício para forçar a atualização remota: Você irá criar um ramo local a partir
de um dos seus diretórios (em lugar de tentar fazer o commit dele para o ramo remoto). Por exemplo, suponha que vamos fazer
o commit do diretório `infraestrutura/heroku/service-usuarios-1`: 

```
git subtree split --prefix infraestrutura/heroku/service-usuarios-1 -b service-usuarios-1
```

Com isso nós criamos um ramo local, chamado `service-usuarios-1` a partir do diretório `infraestrutura/heroku/service-usuarios-1`.
Agora vamos trocar o ramo de trabalho para o ramo recém criado:

```
git checkout service-usuarios-1
```

E, por fim, vamos colocar esse ramo no heroku, com a opção `-f`, para dizer que o obojetivo não é fundir o ramo, mas subsitutir
qualquer cois que tenha no ramo master remoto:

```
git push heroku-servico-usuarios-1 service-usuarios-1:master -f
```

Esses três passos devem ser repetidos toda vez que o heroku rejeitar o seu `push`.

Ao final, volte para o ramo local original (por exemplo, master), e apague o ramo que acabou de criar:

```
git checkout master
git branch -D service-usuarios-1
```


