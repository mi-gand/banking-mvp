FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/deal-0.0.1-SNAPSHOT.jar app.jar

#ENTRYPOINT ["sh", "-c", "sleep 3600"]
#ENTRYPOINT ["java", "-jar", "app.jar"]

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]