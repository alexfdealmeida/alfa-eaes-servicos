## Conteúdo dos diretórios

Aqui temos o mesmo serviço monolítico apresentado no diretório pai, porém transformando a parte
de usuários em um serviço, com uso do SOAP.

Temos 2 subdiretórios neste local:

- BdUnificada
- BdDistribuida

### Base de Dados Unificada

Nesta abordagem temos a base de dados de usuários ainda no mesmo banco. Assim temos ainda 
disponíveis as ferramenas do SGBD, como os produtos cartesianos (`joins`).

### Base de Dados Distribuída

Nessa abordagem a base de dados de usuários é distribuída (o serviço possui uma BD própria), 
o que elimina parte do apoio do SGBD trazendo um pouco em complexidade ao sistema.
Entretanto há ganhos em termos de escalabilidade e especialidade.

## Observações Importantes

Nos projetos, várias classes estão sendo geradas automaticamente por plugins do Maven. Um ponto
crítico é que o servidor precisa estar rodando para o cliente gerar as suas classes, pois ele
pega o wsdl gerado pelo servidor e produz as suas próprias classes a partir dele.

## Atividades Propostas

1. No banco de dados distribuído: Na separação do banco para a montagem desta atividade, os usuários
ficaram em uma base de dados e as tarefas em outra. Na base de dados dos usuários foi criada uma
tabela para ligar os usuários às tarefas (a relação Tarefa está no outro banco). Entretanto, é
mais comum que não se mexa na base de usuários. Faz mais sentido criar essa tabela de relacionamento
do lado da aplicação, no caso, na base de dados de tarefas. Modifique o projeto para que fique assim.

2. No banco de dados unificado:

    2.1. Observe que existem poucas operações sendo efetuadas pelo serviço. Grande parte do trabalho
    ainda está sendo feito pelos métodos da aplicação SPA. Escolha 2 métodos (serviços) da SPA e
    transfira-os para o serviço.
    
    2.2. Crie uma aba nova na SPA para permitir que os usuários comentem sobre uma tarefa. Como inspiração, 
    veja como é a tela de comentários (_issues_) do Github. Faça uma versão mais simples.


