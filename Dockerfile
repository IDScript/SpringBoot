FROM openjdk:19-jdk-alpine3.16

LABEL org.opencontainers.image.authors="KAnggara75"

COPY .env .env

COPY target/springApp-0.1.0-alpha0074.war /springApp-0.1.0-alpha0074.war

ENTRYPOINT ["java","-jar","/springApp-0.1.0-alpha0074.war"]
