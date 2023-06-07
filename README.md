# Poseidon App

Poseidon, an app designed to facilitate transactions.

## Versions

- Java version: 17 
- Spring Boot version: 3.0.6 
- Maven version: 3.9.1
- JDK version: 17 (recommended)

## Database

- Make sure that you have MySQL installed
- Open the resources/application.properties file in your Spring Boot project.
- Complete the configuration with your own password 
- Ensure that the MySQL server is running. Connect to your MySQL server using the command-line client:
  `mysql -u your_username -p`
- Create the database by executing the following command: `CREATE DATABASE demo;`

## Run the app

To build the project, run the following command:
`mvn clean install`

To run the application, use the following command:
`mvn spring-boot:run`

The application will be accessible at `http://localhost:8080`.

You can log in with user or admin account: 
- user - password: pass
- admin - password: pass



