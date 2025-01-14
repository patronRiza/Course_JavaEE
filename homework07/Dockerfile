FROM maven:3.9.5-eclipse-temurin-21
LABEL authors="rizva"

COPY target/homework07-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

COPY src/main/resources/Figures.json Figures.json

ENTRYPOINT ["java", "-jar", "app.jar"]