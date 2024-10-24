<h1 align="center">AstroShelf</h1>
<p align="center">Library Management System</p>

## Description

The **Library Management System** is a platform designed to streamline the management of book loans, library members, and book availability. It provides tools for both users and administrators (librarians) to optimize library administration, inventory control, and loan tracking.

## Features

- **Book Management (CRUD)**: Allows librarians to add, edit, delete, and list books. It includes the management of authors and categories.
- **Library Member Registration**: Users can register as members to access the library's loan services.
- **Book Loans and Returns**: Functionality to allow members to borrow and return books, tracking the status of each loan.
- **Inventory Control and Availability Alerts**: A system to monitor book availability and notify users when a book is unavailable.
- **Fines and Payment Management**: Users can be fined for late book returns, and the system manages and tracks payments.
- **Admin Dashboard for Librarians**: A centralized dashboard where librarians can manage all aspects of the system, including books, members, loans, and fines.

## Technologies

This project was developed using the following technologies:

### Backend
- **Spring Boot**: The primary framework for backend development.
- **JPA (Java Persistence API)**: For managing data persistence and interacting with the database.
- **Spring Security**: For managing security, authentication, and user authorization (members and librarians).
- **H2 Database**: An in-memory database for simplifying development and testing.

### Frontend
- **Thymeleaf**: A template engine for dynamic HTML page rendering.
- **Bootstrap**: A CSS framework for responsive, attractive user interface design.
