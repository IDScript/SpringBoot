#!/bin/sh

main(){
# Check env file
  if [ ! -f .env ]
  then
      echo ".env Not File found, Create from Example"
      cp .env.example .env
  else
      echo ".env File found"
  fi
  export $(cat .env | xargs)
  mvn clean spring-boot:run
}

main
