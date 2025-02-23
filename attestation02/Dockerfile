FROM openjdk:21
LABEL authors="rizva"

COPY target/attestation02-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080