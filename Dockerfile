# Use the official OpenJDK base image
FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*jar

# Copy the Spring Boot JAR file into the container

COPY target/socialMediaRestAPI-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application will run on
EXPOSE 9090

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
