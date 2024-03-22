# JSF-Authentication
A simple JavaServer Faces (JSF) web application that demonstrates user registration and authentication using Hibernate and MySQL.
The application is built using the Model-View-Controller (MVC) design pattern.

## Features

- User registration with basic field validation
- User authentication with basic field validation
- Responsive design using Bootstrap

## Technologies

- JavaServer Faces (JSF)
- Hibernate
- MySQL Server
- GlassFish Server 
- Bootstrap 5.3.3

## Prerequisites

- Java Development Kit (JDK) 11 or later
- Maven 3.6.3 or newer (for building the project)
- MySQL Server 8.0 (database setup)
- GlassFish Server 7.0.13 (deployment)

## Setup and Installation

1. Clone the repository:
    ```
    git clone https://github.com/ismailza/JSF-Authentication.git
    ```
2. Create a MySQL database named `jsfauth` and import the initial schema.

3. Update `src/main/resources/hibernate.cfg.xml` with your database connection details.

4. Deploy the application to GlassFish Server.

5. Access to the application in your web browser.
