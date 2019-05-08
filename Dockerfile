FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
COPY application.properties application.properties
ENTRYPOINT ["java","-Dspring.config.location=/application.properties","-jar","/app.jar"]