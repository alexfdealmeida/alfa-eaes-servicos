# Repositório da Disciplina de SOA da pós-graduação do UNIALFA

Repositório destinado aos alunos pós-graduação do UNIALFA.

## Como usar este repositório

Visite cada um dos diretórios disponíves. Cada um deles possui uma implementação diferente
do mesmo sistema:

- Sistema Monolítico
- Sistema com SOAP
- Sistema com REST
- Sistema com infraestrutura do Springcloud

## Ambiente de Desenvolvimento

1. Java: [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Como toda a parte do backend do projeto é feita em Spring, recomendo que seja usada a 
distribuição do Eclipse fornecida pela equipe do Spring: [Spring Tool Suit - STS](https://spring.io/tools/sts/all)
3. Plugin do Lombok (Gerador de Classes - getters e setters): https://projectlombok.org/
4. NodeJs (para os projetos que envolvem `@angular`): https://nodejs.org/en/
    - Recomendo o uso do editor de código da Microsoft para o projeto `@angular`: [VSCode](https://code.visualstudio.com/)
5. Git: https://git-scm.com/downloads
    - Se não estiver familiarizado com o Git, existe muito material na Internet a respeito.
	Caso tenha interesse, gravei alguns [vídeos rápidos para os alunos da graduação](https://www.youtube.com/playlist?list=PL0fa6_HZDwa3QF8jG0viU2IK-4NfaKDNV)

## Como rodar o projeto

Em cada projeto talvez haja alguma instrução específica a respeito de sua compilação e execução.
Entretanto, todos seguem um passo-a-passo mais ou menos igual:

### 1. Clone o repositório na sua máquina: 

```git
git clone https://github.com/julianobrasil/servicos-pos-unialfa.git
```

Ou faça um `fork` do projeto na sua conta do git, primeiro, fique à vontade a respeito da abordagem
que preferir.

### 2. Importe o projeto no Eclipse

**SÃO PROJETOS MAVEN**, portanto é  preciso importá-los como tal. Veja a imagem abaixo como isso
pode ser feito.
	
![image](https://media.giphy.com/media/3o6fIYvzlzbqP7jx04/giphy.gif)

## Segurança

Devido à extensão do assunto, uma decisão tomada foi a de não adicionar ao código, já extenso, funções 
de segurança. Entretanto, como sei que pode ser de interesse de alguns, a parte de segurança pode
ser implementada. O material listado abaixo é bom (assisti aos vídeos ou li os textos), porém geralmente
a funcionalidade de segurança depende muito da arquitetura adodata em cada caso. Assim, os links
de textos e vídeos que coloquei aqui devem ser encarados apenas como peças para se construir uma
solução completa.

### Backend (Spring)

1. [![Vídeo da Algaworks](https://img.youtube.com/vi/FOX0r52_hwE/0.jpg)](https://www.youtube.com/watch?v=FOX0r52_hwE)

### Frontend (No projeto @angular)

1. [Route Guards](https://angular.io/guide/router#milestone-5-route-guards)

## Em caso de dúvidas

Caso tenha dúvidas, fique à vontade para [criar uma issue no repositório deste projeto no Github](https://github.com/julianobrasil/servicos-pos-unialfa/issues).

Se você não esta acostumado a trabalhar com o Github, uma issue permite que todos compartilhem 
dúvidas e opinem sobre possíveis soluções para a questão.

Peço que as issues sejam criadas para assuntos estritamente ligadas aos projetos. Qualquer
outro tipo de pergunta pode ser enviada para: soa@unialfa.com.br


