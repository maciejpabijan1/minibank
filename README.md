# MiniBank

MiniBank is a web application built with Spring Boot that simulates basic online banking operations. The project was created to practice layered application architecture, business logic validation, user authentication, and transactional data management.

## Features

* User authentication with session management (HttpSession)
* Secure password storage using SHA-256 hashing
* Account overview with balance and account information
* Money transfers between accounts
* Business rule validation:

    * transfer amount must be greater than zero
    * sender and recipient accounts must be different
    * sender must have sufficient funds
* Error handling with user-friendly messages
* Transactional transfer processing using `@Transactional`

## Technologies

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* Thymeleaf
* H2 Database
* Maven

## Architecture

The application follows a layered architecture:

* Controller Layer – handles HTTP requests and responses
* Service Layer – contains business logic and transfer validation
* Repository Layer – database access through Spring Data JPA
* Entity Layer – domain model representing bank accounts

## Sample Accounts

| Account Number | Password |
| -------------- | -------- |
| 11111111111    | 123      |
| 22222222222    | haslo    |

## Running the Application

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Run `MinibankApplication`
4. Open:

```text
http://localhost:8080
```

## Learning Goals

This project was created to gain practical experience with:

* Spring Boot application development
* Layered architecture
* Database operations with JPA
* Transaction management
* Session-based authentication
* Validation of business rules
