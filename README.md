# Gestion-de-Personnel-Authentification-avec-Spring-Security---
A Spring Boot application that manages employees with full CRUD operations. It includes basic authentication using Spring Security, role-based access, global exception handling, employee search, and uses a persistent H2 database in file mode.


Project Description
This project is a Spring Boot application that manages a CRUD system for employees, including:

Basic authentication using Spring Security.

Global exception handling for consistent error responses.

Employee search by name with custom error management.

Persistent H2 database in file mode for data storage
Main Features:
Authentication:

Basic HTTP authentication with a user stored in memory (admin/admin123).

Spring Security configuration to protect routes and allow access to /h2-console/**.

Global Exception Handling:

Centralized management of errors using @RestControllerAdvice.

Structured JSON responses for exceptions (status, error, message).

Employee Management:

Full CRUD operations: create, read, update, delete.

Search employees by name.

Count the total number of employees.

Database:

H2 database configured in file mode (jdbc:h2:file:./data/personnel-db).

Data persists across application restarts.

Technologies Used : 
  Spring Boot

  Spring Security

  H2 Database

  Java 17+

  Maven
