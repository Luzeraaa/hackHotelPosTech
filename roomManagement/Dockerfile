FROM eclipse-temurin:17-jdk-alpine

LABEL authors="Angelo Cavalcanti"

VOLUME /tmp

COPY roomManagement-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]