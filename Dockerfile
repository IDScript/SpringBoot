FROM maven:3.8.7-openjdk-18-slim AS build

LABEL org.opencontainers.image.authors="KAnggara75"

COPY src /home/app/src

COPY pom.xml /home/app

COPY .env.docker /home/app/.env

RUN mvn -f /home/app/pom.xml clean package

EXPOSE 8080

ENTRYPOINT ["java","-jar","/home/app/target/budgetin.jar"]
