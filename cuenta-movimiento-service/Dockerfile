FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/cuenta-movimiento-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
