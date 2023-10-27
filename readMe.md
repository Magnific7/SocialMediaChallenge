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
    git clone https://github.com/yourusername/social-media-rest-api.git
    ```

2. Change to the project directory:

    ```shell
    cd social-media-rest-api
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

```shell
mvn spring-boot:run
