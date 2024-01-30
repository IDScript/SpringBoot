#!/bin/sh

# Get ad set package version
version(){
  VER=`gitversion | grep NuGetVersionV2 | cut -d'"' -f 4`
  sed "s/9.9.9/${VER}/g" pom.xml > pom2.xml
  mv pom2.xml pom.xml
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
  git restore .
  git pull
  export $(cat .env | xargs)
  version
  mvn clean package
  mv target/budgetin.jar ~/app/budgetin.jar
}

main
