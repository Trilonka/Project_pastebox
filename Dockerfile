FROM openjdk:17-jdk-alpine

MAINTAINER Trilonka

COPY target/Project_pastebox-0.0.1-SNAPSHOT.jar pastebox.jar

ENTRYPOINT ["java", "-jar", "/pastebox.jar"]