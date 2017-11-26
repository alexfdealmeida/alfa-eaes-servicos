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
o que faz com que elimina parte do apoio do SGBD trazendo um pouco em complexidade ao sistema.
Entretanto há ganhos em termos de escalabilidade e especialidade.

### Observações Importantes

Nos projetos, várias classes estão sendo geradas automaticamente por plugins do Maven. Um ponto
crítico é que o servidor precisa estar rodando para o cliente gerar as suas classes, pois ele
pega o wsdl gerado pelo servidor e produz as suas próprias classes a partir dele.
