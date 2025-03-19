FROM maven:3.8.6-eclipse-temurin-17 AS build

ARG BOOTSTRAP_SERVERS=${BOOTSTRAP_SERVERS}

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM eclipse-temurin:17-jre
COPY target/kafka-msk-1.0-SNAPSHOT.jar /usr/local/lib/app.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]

