FROM maven:3.8.6-eclipse-temurin-17 AS build

WORKDIR /home/app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /usr/local/lib

COPY --from=build /home/app/target/kafka-msk-1.0-SNAPSHOT.jar app.jar

ARG BOOTSTRAP_SERVERS=${BOOTSTRAP_SERVERS}

ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
