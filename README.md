# Spring Project Template 🌱

## Overview

The Spring Project Template is a kickstart project designed to accelerate the development of your applications using Spring Boot version 3.2.0 and Java 17. This template includes user registration and authentication, implementing JWT tokens and Spring Security, providing a secure foundation for your application.

## Features 🚀

- **Spring Boot 3.2.0:** Utilizes the latest version of Spring Boot for enhanced features and performance.

- **Java 17:** Developed using Java 17 to leverage the latest language capabilities and improvements.

- **User Registration and Login:** Integrated user registration and login functionalities to jumpstart user management.

- **JWT Tokens:** Implements JSON Web Tokens (JWT) for secure authentication and authorization.

- **Spring Security:** Ensures robust security measures to protect your application.

- **Swagger Documentation:** This project comes with a Swagger UI to document your API endpoints. Access the Swagger UI [here](http://localhost:8080/swagger-ui.html) after you run the application.

## Getting Started 🛠️

Follow these steps to get the project up and running on your local machine.

### Prerequisites

- [Java 17](https://openjdk.java.net/projects/jdk/17/): Ensure you have Java 17 installed on your system.

- [Maven](https://maven.apache.org/): Make sure Maven is installed to manage project dependencies.

- [MySQL](https://www.mysql.com/): Install MySQL to set up the database.

- [Postman](https://www.postman.com/): Install Postman to test the API endpoints.

- [Git](https://git-scm.com/): Install Git to clone the repository.

## Usage 🚦

### Using the Template

1.  **Navigate to the Template Repository:**
    Visit the [Spring Project Template Repository](https://github.com/mirukibs/Spring-Project-Template) on GitHub.
    ![Screenshot from 2024-01-05 14-14-29.png](assets%2FScreenshot%20from%202024-01-05%2014-14-29.png)

2.  **Click on the "Use this template" button:**
    ![Screenshot from 2024-01-05 14-14-51.png](assets%2FScreenshot%20from%202024-01-05%2014-14-51.png)

3. **Click on the "Create a new repository" button:**

    ![Screenshot from 2024-01-05 14-15-28.png](assets%2FScreenshot%20from%202024-01-05%2014-15-28.png)

4. **Provide a Repository Name and Description:**
    Set a name for your new repository based on the template. Optionally, add a description.
    ![Screenshot from 2024-01-05 14-16-41.png](assets%2FScreenshot%20from%202024-01-05%2014-16-41.png)

5. **Complete setting up your repository:**
    Set up your repository by selecting the visibility, adding collaborators, and initializing the repository with a README. Include other setups of your choice then click on the "Create repository" button.

## Contributing 🤝

If you find issues or have suggestions for improvements, feel free to open an issue or create a pull request.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/mirukibs/Spring-Project-Template.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Spring-Project-Template
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   java -jar target/Spring-Project-Template.jar
   ```

### Configuration ⚙️

Customize the application according to your needs by modifying the configuration files located in the `src/main/resources` directory.

### Database Configuration

Configure your database connection settings in the `application.properties` file as well.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/{your_db}?user={your_db_username}&password={your_db_password}
spring.datasource.username=${your_db_username}
spring.datasource.password=${your_db_password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

---
