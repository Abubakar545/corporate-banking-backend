FROM openjdk:17-jdk-slim
LABEL authors="sayed"

COPY target/loan-application-service-0.0.1-SNAPSHOT.jar loan-application-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "loan-application-service-0.0.1-SNAPSHOT.jar"]