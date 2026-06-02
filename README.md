# MiniBank

A simple online banking web application built with **Spring Boot**. The main goal of this project is to practice clean architecture, business logic validation, and database safety.

## 🚀 Features
* **User Login:** Secure login system with password hashing.
* **Account Overview:** View current balance, account number, and user profile details.
* **Money Transfers:** Send money between different bank accounts.
* **Smart Validation:** All business rules (e.g., checking if the amount is greater than 0 or if the sender has enough money) are handled safely in the Service layer.
* **Data Safety:** Uses `@Transactional` to ensure that bank transfers either succeed completely or make no changes to the database if an error occurs.

## 🛠️ Technologies Used
* **Language:** Java
* **Framework:** Spring Boot (Spring Web, Spring Data JPA)
* **Database:** H2 (In-Memory Database)
* **Template Engine:** Thymeleaf (HTML)

## ⚙️ How to run
1. Open the project in IntelliJ IDEA.
2. Run the `MinibankApplication` class.
3. Open `http://localhost:8080` in your browser.