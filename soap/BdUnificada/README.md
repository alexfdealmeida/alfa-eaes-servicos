# Base de Dados Unificada

Existem 2 projetos nesta área:

- Sistema monolítico modificado
    - O SPA foi aqui modificado para solicitar o usuário usando o seu id por meio de um serviço.
    Essa requisição é feita quando o cliente clica no icone do lapis para editar o usuario.
- Serviço de Usuários
    - Foi disponibilizado 1 serviço no projeto, com 3 operações:
        - **1 para demonstração**: obtenção dos usuários por parte do nome. Esta operação não está 
	sendo usada no cliente. Ela apenas solicita todos os usuários cujo nome possuem o token
	"an" e os escreve no console do cliente
	
	    - **1 para uso do cliente, relativo a usuários**: obtém um usuário por id
	    
	    - **1 pra usdo do cliente, relativo à tarefas**: inicialmente a ideia era conter somente
	    serviços de usuários no sistema. Porém, para o bem do entendimento, foi incluída também
	    uma operação que obtém todas as tarefas

A abordagem do SOAP usada aqui foi a _Contract First_ ("O contrato é mais importante"): o que é feito
pelo desenvolvedor é concentrar esforços no desenovolvimento dos arquivos `.xsd` e deixar a geração
das classes de transporte para plugins do maven.

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


## Observações Importantes
 
- Em ambos os projetos estamos usando um plugin do Maven (conforme orientação da documentação do Spring) para a geração das classes usadas na transmissão e recebimento das mensagens SOAP: 
    - Servidor: [JAXB2](http://www.mojohaus.org/jaxb2-maven-plugin/Documentation/v2.2/index.html)
    - Cliente: [JAXB2](https://github.com/highsource/maven-jaxb2-plugin)
    
- O JAXB2 basicamente gera classes java a partir de arquivos XML
    
```xml
<!-- plugin do Maven do lado do servidor -->
<plugin>
   <groupId>org.codehaus.mojo</groupId>
   <artifactId>jaxb2-maven-plugin</artifactId>
   <version>1.6</version>
   <executions>
      <execution>
         <id>xjc</id>
         <goals>
            <goal>xjc</goal>
         </goals>
      </execution>
   </executions>
   <configuration>
      <!-- Pasta onde ficará os arquivos .xsd -->
      <schemaDirectory>${project.basedir}/src/main/resources/schemas/</schemaDirectory>
	
      <!-- Pasta onde serão geradas as classes -->
      <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
      <clearOutputDir>false</clearOutputDir>
      
      <!-- Arquivo que será processado -->
      <schemaFiles>main.xsd</schemaFiles>
   </configuration>
</plugin>
```

```xml
<!-- plugin do Maven do lado do cliente -->
<plugin>
   <groupId>org.jvnet.jaxb2.maven2</groupId>
   <artifactId>maven-jaxb2-plugin</artifactId>
   <version>0.12.3</version>
   <executions>
      <execution>
         <goals>
            <goal>generate</goal>
         </goals>
      </execution>
   </executions>
   <configuration>
      <schemaLanguage>WSDL</schemaLanguage>
      <!-- arquivo xml que será gerado a partir do serviço descrito pelos servidor -->
      <generatePackage>servicos.wsdl</generatePackage>
      <schemas>
         <schema>
	    <!-- Endereço do servidor onde conseguir o a descrição do serviço -->
            <url>http://localhost:9000/ws/service.wsdl</url>
         </schema>
      </schemas>
   </configuration>
</plugin>
```

- Além disso, no servidor, também é preciso incluir uma dependência que possibilita o uso da tag
`<xs:include />` para a compilação de um arquivo único contendo as definições de cada serviço
oferecido:

```xml
<dependency>
   <groupId>org.apache.ws.xmlschema</groupId>
   <artifactId>xmlschema-core</artifactId>
   <version>2.2.2</version>
</dependency>
```


