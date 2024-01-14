#!/bin/sh

# Get ad set package version
VER=`gitversion | grep NuGetVersionV2 | cut -d'"' -f 4`
OUTPUT=springApp-${VER}.war

sed "s/\<version\>0.0.1.*/\<version\>${VER}\<\/version\>/" pom.xml > pom2.xml
mv pom2.xml pom.xml

# Creata java package
mvn clean package
mv target/*.war target/${OUTPUT}

# Prepare Dockerfile for new package
sed "s/target\/.*/target\/${OUTPUT} \/${OUTPUT}/" Dockerfile > Dockerfile.txt
sed "s/app\.war.*/${OUTPUT}\"\]/" Dockerfile.txt > Dockerfile
rm Dockerfile.txt

# # Build Docker image
docker container prune
docker image prune
docker build -t kanggara75/spring:${VER} .
