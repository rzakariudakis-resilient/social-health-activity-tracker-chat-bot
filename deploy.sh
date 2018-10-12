#!/bin/bash
mvn clean install

docker rmi shat-bot-api

docker build -t shat-bot-api .

gcloud app deploy app.yml -q --promote