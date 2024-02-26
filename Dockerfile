FROM maven:3.8.7-openjdk-18-slim AS build

LABEL org.opencontainers.image.authors="KAnggara75"

COPY .env.docker .env

COPY target/budgetin.jar budgetin.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","budgetin.jar"]
