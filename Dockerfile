FROM openjdk:8-jdk-alpine

MAINTAINER antoine.godeau@sfr.fr

VOLUME /tmp
COPY target/*.jar fortnite-generation.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/fortnite-generation.jar"]
