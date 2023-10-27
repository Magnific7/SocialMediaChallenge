# Social Media Rest API

**Social Media Rest API** is a Kotlin-based Spring Boot application that provides APIs for a social media platform. This README will guide you on how to set up and run the application.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17.
- Apache Maven for building and managing the project.
- PostgreSQL database for storing application data.
- Code editor or Integrated Development Environment (IDE) of your choice.

## Getting Started

Follow these steps to set up and run the Social Media Rest API.

### Installation

1. Clone the repository to your local machine:

    ```shell
    git clone git@github.com:Magnific7/SocialMediaChallenge.git
    ```

2. Change to the project directory:

    ```shell
    cd SocialMediaChallenge
    ```

3. Build the application using Maven:

    ```shell
    mvn clean install
    ```

### Configuration

1. Configure your PostgreSQL database connection by modifying the `application.properties` file in the `src/main/resources` directory.

2. Set environment variables or application properties as needed for authentication and other configurations.

## Usage

To run the application, use the following command:

## Running the Application with Docker

To run the Social Media Rest API application using Docker, follow these steps:

1. Build the Docker image by running the following command in the project's root directory:

   ```shell
   docker build -t social-media-api .
   ```
   ```
   docker compose up
   ```
#### 2.Running the IDE (Intellig)
To run the application in an Integrated Development Environment (IDE) like IntelliJ IDEA, follow these steps:

1.Open the Project: Open your IDE and import the project by selecting the project's root directory.

2.Configure JDK: Ensure that the project is configured to use JDK 17. If not, set it as the project's Java SDK in the IDE.

3.Configure Maven: Set up your IDE to use Apache Maven for building and managing the project.

4.Update Run Configuration: Modify your run configuration to use the Spring Boot application class as the main class for the application.

5.Run the Application: Start the application from your IDE by running the modified run configuration.

### Accessing Swagger Documentation
After the application is up and running, you can explore the API endpoints and test them using the Swagger documentation. Access the Swagger documentation by opening your web browser and navigating to the following URL:

http://localhost:9090/swagger-ui/index.html

## API Documentation
Once the application is up and running, you can explore the API endpoints and test them using the Swagger documentation. Access the Swagger documentation by navigating to the following URL in your browser:
