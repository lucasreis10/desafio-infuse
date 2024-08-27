# desafio-infuse

Desafio técnico.


# Links
- [Local](http://localhost:8080)
- [Github](https://github.com/lucasreis10/desafio-infuse)
- [Swagger](http://localhost:8080/swagger-ui/index.html)

## Pre requisitos para execução local

É necessario instalar as seguintes ferramentas para executar o projeto localmente:

* [JDK-22](https://www.oracle.com/br/java/technologies/downloads/#java22)
* [docker](https://docs.docker.com/engine/install/)
* [docker-compose](https://docs.docker.com/compose/install/)

## Comandos importantes

### Rodar testes

<br/>
<i> No diretório raiz do projeto execute: </i>
<br/>

```shell script 
 ./gradlew test -i
 ```

### Rodar instância do MYSQL

<br/>
<i> No diretório raiz do projeto execute: </i>
<br/>

```shell script 
 docker-compose up -d
 ```
