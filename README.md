# Spring Crud Template 🌱

## Overview

The Spring Crud Template is a kickstart project designed to accelerate the development of CRUD (Create, Read, Update, Delete) applications using Spring Boot version 3.2.0 and Java 17. This template includes a user registration and login feature with JWT tokens and Spring Security, providing a secure foundation for your application.

## Features 🚀

- **Spring Boot 3.2.0:** Utilizes the latest version of Spring Boot for enhanced features and performance.

- **Java 17:** Developed using Java 17 to leverage the latest language capabilities and improvements.

- **User Registration and Login:** Integrated user registration and login functionalities to jumpstart user management.

- **JWT Tokens:** Implements JSON Web Tokens (JWT) for secure authentication and authorization.

- **Spring Security:** Ensures robust security measures to protect your application.

- **CRUD Operations:** Boilerplate code for basic CRUD operations, allowing developers to focus on business logic.

## Getting Started 🛠️

Follow these steps to get the project up and running on your local machine.

### Prerequisites

- [Java 17](https://openjdk.java.net/projects/jdk/17/): Ensure you have Java 17 installed on your system.

- [Maven](https://maven.apache.org/): Make sure Maven is installed to manage project dependencies.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/mirukibs/Spring-Crud-Template.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Spring-Crud-Template
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   java -jar target/Spring-Crud-Template.jar
   ```

## Configuration ⚙️

Customize the application according to your needs by modifying the configuration files located in the `src/main/resources` directory.

### Database Configuration

Configure your database connection settings in the `application.properties` file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

[//]: # (### JWT Configuration)

[//]: # ()
[//]: # (Adjust JWT-related settings in the `application.properties` file.)

[//]: # ()
[//]: # (```properties)

[//]: # (jwt.secret=your_jwt_secret)

[//]: # (jwt.expiration=86400000)

[//]: # (```)

## Usage 🚦

Use this as a template for your project, ready loaded with user registration and authentication as well as a structured directory for your files.

## Contributing 🤝

If you find issues or have suggestions for improvements, feel free to open an issue or create a pull request.

## License 📝

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---