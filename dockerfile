FROM openjdk:17-jdk-alpine
COPY target/SistContVehiculos-0.0.1-SNAPSHOT.jar SCV-app.jar
ENTRYPOINT ["java", "-jar", "SCV-app.jar"]