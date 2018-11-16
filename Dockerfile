FROM gcr.io/google-appengine/openjdk:8

COPY target/api-1.0.0-SNAPSHOT.jar app.jar
CMD [ "java", "-jar","app.jar"]
