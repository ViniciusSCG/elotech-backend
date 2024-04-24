FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y \
    openjdk-17jdk -y \

COPY . .

RUN apt-get install maven -y

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target\demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]