#!/bin/bash
mvn clean package

docker build -t shat-bot-api .

gcloud app deploy app.yml -q