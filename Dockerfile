FROM openjdk:8-jdk-alpine

# atualiza dependencias e instala o maven
RUN apk update

RUN apk add maven

COPY . .
#faz o build da imagem
RUN mvn clean package

# transforma o executavel em uma imagem
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]