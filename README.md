# Comunicação API
## Para executar com Docker
Requisitos mínimos: 
- Docker v18+

Como rodar:
 - Clone o projeto e rode o script docker-run.bat
  ```sh
docker-run.bat
```
Como testar:
 - Basta acessar no navegador: http://localhost:8080/swagger-ui.html#/ após o encerramento do Script
 

## Para executar local

Requisitos mínimos: 
- JDK v8+
- Maven v3+

Como rodar:
 - Clone o projeto e no terminal rode os seguintes comandos:
 ```sh
mvn clean install -U
mvn spring-boot:run
```
 
Como testar:
 - Basta acessar no navegador: http://localhost:8080/swagger-ui.html#/
 
