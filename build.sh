#!/bin/sh

# Get ad set package version
version(){
  VER=`gitversion | grep NuGetVersionV2 | cut -d'"' -f 4`
  OUTPUT=springApp-${VER}.war

  sed "s/\<version\>0.0.1.*/\<version\>${VER}\<\/version\>/" pom.xml > pom2.xml
  mv pom2.xml pom.xml
}

# Creata java package
create_package(){
  mvn clean package
  mv target/*.war target/${OUTPUT}
}

# Prepare Dockerfile for new package
create_docker_file(){
  sed "s/target\/.*/target\/${OUTPUT} \/${OUTPUT}/" Dockerfile > Dockerfile.txt
  sed "s/app\.war.*/${OUTPUT}\"\]/" Dockerfile.txt > Dockerfile
  rm Dockerfile.txt
}

# Build Docker image
build_docker_image(){
  docker container prune
  docker image prune
  docker build -t kanggara75/spring:${VER} .
}

main(){
  # Check env file
  if [ ! -f .env ]
  then
      echo ".env Not File found, Create from Example"
      cp .env.example .env
  else
      echo ".env File found"
  fi
  version
  create_package
  create_docker_file
  build_docker_image
}

main
