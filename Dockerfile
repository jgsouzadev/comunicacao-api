FROM openjdk:8-jdk-alpine

# atualiza dependencias e instala o maven
RUN apk update

RUN apk add maven

COPY . .
#faz o build da imagem
RUN mvn clean install -U

#apaga o cache do container
RUN rm -rf /var/cache/apk/*

# transforma o executavel em uma imagem
COPY *.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]