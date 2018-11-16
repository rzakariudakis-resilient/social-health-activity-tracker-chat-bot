# S.H.A.T. Bot
## Social Health Activity Tracker Bot for google chat


## Building and running
mvn clean package

docker rmi shat-bot-api

docker build -t shat-bot-api .

docker run -p 8080:8080 -m 256MB -t shat-bot-api:latest

gcloud app deploy app.yml

#Building and Deploying
mvn clean package

docker build -t shat-bot-api .

gcloud app deploy app.yml