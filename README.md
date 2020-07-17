Delivery Service API Rest
===================================

Java JDK versão 1.8

### Como rodar a aplicação Spring Boot?

* Esse projeto utiliza o Maven para gestão de suas dependências. O comando a seguir instala as suas dependências e o empacota.

```
mvn clean install package
```

* A arquitetura da API é dividida nas seguintes camadas:

```
@Repository - Camada reposável pelo acesso direto ao banco de dados.

@Service - Nessa camada intermediária é implementada a maioria as regras de negócio da aplicação. A partir dela acessamos a cada de repositórios.  

@Controller - Essa camada possui todos os end-points da API Rest, ela é a camada de acesso primária e a partir dela temos acesso a camada de serviços. 
 
```

* O arquivo application.propperties possui a conexão com o banco de dados, configuração do servidor de e-mail e a configuração de acesso ao broker do RabbitMQ. Essa aplicação utiliza o banco de dados embarcado H2, sendo assim todos os dados são armazenados em memória o que torna os testes em desenvolvimento muito mais ágeis. A configuração de e-mail é necessária para o disparo de mensagens. Deve-se colocar o nome de usuário e senha do seu servidor de e-mail e lembrar de habilitar o acesso a aplicativos menos seguros. O broker RabbitMQ é possui um nome de usuário e senha padrão quando executado em localhost mas pode-se criar usuários personalizados.  

```application.propperties

spring.datasource.url=jdbc:h2:mem:data;DB_CLOSE_ON_EXIT=FALSE;MODE=Oracle;INIT=CREATE SCHEMA IF NOT EXISTS DELIVERY_SERVICE
spring.datasource.platform=h2
spring.jpa.hibernate.ddl-auto=none
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.datasource.initialization-mode=always

spring.mail.host = smtp.gmail.com
spring.mail.username = ******@gmail.com
spring.mail.password = ******
spring.mail.properties.mail.smtp.auth = true
spring.mail.properties.mail.smtp.socketFactory.port = 465
spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback = false

spring.rabbitmq.host=localhost
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.port=5672
spring.rabbitmq.virtual-host=/

```

* Banco de Dados Embarcado

```schema.sql

Esse arquivo possui o nosso banco de dados embarcado. Ele funciona como um script vivo que irá armazenar desde a criação das tabelas até os comando de insert, update e delete executados através da API. Aqui tbm podemos popular as tabelas e antes mesmo de subir a aplicação. 

CREATE SCHEMA IF NOT EXISTS DELIVERY_SERVICE;

CREATE TABLE DELIVERY_SERVICE.TAB_USER (
  TUS_ID_USER NUMBER(12) NOT NULL,
  TUS_NAME VARCHAR2(20) NOT NULL,
  TUS_MAIL_ADDRESS VARCHAR2(1000) NOT NULL,
  TUS_ADDRESS VARCHAR2(1000) NOT NULL,
  CONSTRAINT PK_USER PRIMARY KEY (TUS_ID_USER)
);

CREATE UNIQUE INDEX DELIVERY_SERVICE.PK_TAB_USER ON DELIVERY_SERVICE.TAB_USER (TUS_ID_USER);

CREATE SEQUENCE DELIVERY_SERVICE.SEQ_USER MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 1;
 
```

* Para testar a aplicação basta rodar a classe principal DeliveryServiceApplication a seguir:

```DeliveryServiceApplication.java

@SpringBootApplication
@EnableAutoConfiguration(
	exclude = {
		SecurityAutoConfiguration.class
	}
)
public class DeliveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryServiceApplication.class, args);
	}
}
```

### Swagger - Documentação e Testes


* O Swagger é uma ferramenta que permite criar documentação para APIs. Essa ferramenta permite que ao mesmo tempo que é criada a API também seja gerado a sua documentação e ainda possibilita para todos os utilizadores o entendimento claro do comportamento oferecido pelo serviço por que possui um fácil acesso, estruturas claras, é interativo e que permite fazer simulações. Após rodar a aplicação ela fica acessível pela seguinte url:

```
http://localhost:8080/swagger-ui.html

```

### Aplicação Angular

* Como rodar a aplicação Angular?

```
Instalação Angula CLI
 
- npm install -g @angular/cli

Instalação node_modules

- npm install

Visualizando no navegador:

- ng serve --port 8081

- Acessar em: http://localhost:8081/ 

```
