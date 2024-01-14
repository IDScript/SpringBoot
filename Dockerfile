FROM openjdk:8-jdk-alpine

LABEL org.opencontainers.image.authors="KAnggara75"

COPY target/docker-message-server-1.0.0.jar message-server-1.0.0.jar

ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]