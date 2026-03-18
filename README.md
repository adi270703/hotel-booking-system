# 🏨 Enterprise Hotel Booking API

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-brightgreen.svg)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue.svg)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-yellow.svg)

A robust, enterprise-level backend REST API for managing hotel room reservations. Built with Spring Boot and MySQL, this project demonstrates core backend engineering principles including layered architecture, transactional safety, and relational database management.

## 🚀 Core Architecture & Features

This application follows strict **Layered Architecture** (Controller -> Service -> Repository -> Entity) to ensure clean separation of concerns and maintainability.

* **Inventory Management:** Add and track hotel rooms dynamically.
* **Smart Filtering:** Retrieve available rooms using Java Streams and database queries.
* **Transactional Booking:** Secure booking engine using Spring's `@Transactional` annotation to prevent double-booking and ensure database integrity during the reservation process.
* **RESTful Standards:** Clean endpoint design using proper HTTP methods (GET, POST) and precise status codes (200 OK, 201 Created, 400 Bad Request).

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3
* **Data Access:** Spring Data JPA / Hibernate
* **Database:** MySQL
* **Build Tool:** Maven
* **API Testing:** Postman

## 📡 API Endpoints

| HTTP Method | Endpoint | Description | Status Code (Success) |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/rooms/available` | Fetches a list of all currently available rooms. | `200 OK` |
| `POST` | `/api/v1/rooms` | Adds a new room to the hotel inventory. | `201 CREATED` |
| `POST` | `/api/v1/bookings/room/{id}` | Books a specific room and marks it as unavailable. | `201 CREATED` |

## 💻 Local Setup & Installation

If you would like to run this project locally, follow these steps:

**1. Clone the repository:**
```bash
git clone [https://github.com/adi270703/hotel-booking-system.git](https://github.com/adi270703/hotel-booking-system.git)