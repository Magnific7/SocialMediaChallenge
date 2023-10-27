# Use the official OpenJDK base image
FROM openjdk:17-jre-slim

# Set the working directory inside the container
WORKDIR /socialMediaRestAPI

# Copy the Spring Boot JAR file into the container
COPY target/socialMediaRestAPI.jar /socialMediaRestAPI/socialMediaRestAPI.jar

# Expose the port your Spring Boot application will run on
EXPOSE 9090

# Command to run the Spring Boot application
CMD ["java", "-jar", "socialMediaRestAPI.jar"]
