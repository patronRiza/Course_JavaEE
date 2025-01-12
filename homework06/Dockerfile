FROM maven:3.9.5-eclipse-temurin-21
LABEL authors="rizva"

# Устанавливаем Maven
#RUN apt-get update && apt-get install -y wget
#RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz
#RUN tar -xvzf apache-maven-3.9.5-bin.tar.gz
#RUN mv apache-maven-3.9.5 /opt/maven
#ENV PATH="/opt/maven/bin:${PATH}"

#1 вариант
#COPY pom.xml pom.xml
#COPY src src
#RUN mvn -f pom.xml clean package -DskipTests

#2 вариант
COPY target/homework06-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]