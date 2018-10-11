docker build -t shat-bot-api .

docker run -p 8080:8080 -m 256MB -t shat-bot-api:latest

gcloud app deploy app.yml