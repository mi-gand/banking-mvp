FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/dossier-0.0.1-SNAPSHOT.jar app.jar

COPY autorisationMail.properties ./
ENTRYPOINT ["java", "-jar", "app.jar"]
