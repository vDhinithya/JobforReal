#1. Base Image
FROM openjdk:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy project files
COPY . /app

# Package the app
RUN ./mvnw clean package -DskipTests

# Expose port (same as in application.properties)
EXPOSE 5050

# Run the JAR file
ENTRYPOINT ["java", "-jar", "target/Job-Portal-0.0.1-SNAPSHOT.jar"]