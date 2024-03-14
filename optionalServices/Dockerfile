FROM eclipse-temurin:17-jdk-alpine

LABEL authors="Felipe Chimin"

VOLUME /tmp

COPY optionalServices-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]