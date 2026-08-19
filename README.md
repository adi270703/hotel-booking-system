**# 🏨 Hotel Booking System - REST API**



**> A production-ready REST API for hotel reservation management with date overlap prevention, transactional integrity, and custom exception handling.**



**\*\*Built with:\*\* Java 11 | Spring Boot 2.7.14 | MySQL 8.0 | Hibernate | REST API**



**---**



**## 📋 Table of Contents**



**- \[Features](#-features)**

**- \[API Endpoints](#-api-endpoints)**

**- \[Setup Instructions](#️-setup-instructions)**

**- \[Project Structure](#-project-structure)**



**---**



**## ✨ Features**



**- ✅ Complete room management (CRUD operations)**

**- ✅ Smart booking system with automatic date validation**

**- ✅ Date overlap prevention at database level**

**- ✅ Guest booking history tracking**

**- ✅ Transactional integrity (ACID compliance)**

**- ✅ Custom exception handling with global error responses**

**- ✅ DTO pattern for clean API contracts**

**- ✅ Production-ready Spring Boot architecture**



**---**



**## 🏗️ Architecture**



**Uses a \*\*layered architecture pattern\*\* with clear separation of concerns:**

**- \*\*Controller Layer\*\* → REST endpoints**

**- \*\*Service Layer\*\* → Business logic \& validation**

**- \*\*Repository Layer\*\* → Data access (Spring Data JPA)**

**- \*\*Entity Layer\*\* → Database models**



**\*\*Key Patterns:\*\* DTO separation, custom exceptions, global error handling, transactional integrity.**



**---**



**## 📚 API Endpoints**



**### Room Operations (`/api/v1/rooms`)**

**| Method | Endpoint | Description |**

**|--------|----------|-------------|**

**| `POST` | `/` | Create room |**

**| `GET` | `/{roomId}` | Get room by ID |**

**| `GET` | `/available` | List available rooms |**

**| `PUT` | `/{roomId}` | Update room |**

**| `DELETE` | `/{roomId}` | Delete room |**



**### Booking Operations (`/api/v1/bookings`)**

**| Method | Endpoint | Description |**

**|--------|----------|-------------|**

**| `POST` | `/` | Create booking (with overlap check) |**

**| `GET` | `/{bookingId}` | Get booking by ID |**

**| `GET` | `/room/{roomId}` | Get bookings for room |**

**| `GET` | `/guest/{guestEmail}` | Get guest booking history |**

**| `GET` | `/` | List all bookings |**

**| `DELETE` | `/{bookingId}` | Cancel booking |**



**---**



**## 📁 Project Structure**



**```**

**src/main/java/com/hotelreservation/**

**│**

**├── controller/**

**│   ├── RoomController.java**

**│   └── BookingController.java**

**│**

**├── service/**

**│   ├── RoomService.java**

**│   └── BookingService.java**

**│**

**├── repository/**

**│   ├── RoomRepository.java**

**│   └── BookingRepository.java**

**│**

**├── entity/**

**│   ├── Room.java**

**│   └── Booking.java**

**│**

**├── dto/**

**│   ├── RoomDTO.java**

**│   ├── BookingDTO.java**

**│   ├── RoomRequest.java**

**│   └── BookingRequest.java**

**│**

**├── exception/**

**│   ├── RoomNotFoundException.java**

**│   ├── DateOverlapException.java**

**│   └── BookingException.java**

**│**

**├── handler/**

**│   └── GlobalExceptionHandler.java**

**│**

**└── HotelReservationApplication.java**



**src/main/resources/**

**├── application.properties**

**├── application-dev.properties**

**└── application-prod.properties**

**```**



**---**



**## ⚙️ Setup Instructions**



**### Prerequisites**

**- Java 11+**

**- Maven 3.6+**

**- MySQL 8.0+**



**### Quick Start**



**1. \*\*Clone the repository:\*\***

**```bash**

**git clone https://github.com/adi270703/hotel-booking-system.git**

**cd hotel-booking-system**

**```**



**2. \*\*Create MySQL database:\*\***

**```sql**

**CREATE DATABASE hotel\_db;**

**```**



**3. \*\*Configure database\*\* - Update `src/main/resources/application.properties`:**

**```properties**

**spring.datasource.url=jdbc:mysql://localhost:3306/hotel\_db**

**spring.datasource.username=root**

**spring.datasource.password=your\_password**

**spring.jpa.hibernate.ddl-auto=update**

**```**



**4. \*\*Run the application:\*\***

**```bash**

**mvn spring-boot:run**

**```**



**Server starts on `http://localhost:8080` ✅**



**---**



**## 🎯 Key Highlights**



**- \*\*Date Overlap Prevention\*\* — Custom JPA queries detect conflicting bookings**

**- \*\*Custom Exceptions\*\* — Domain-specific errors (`RoomNotFoundException`, `DateOverlapException`)**

**- \*\*Global Exception Handling\*\* — Centralized error responses via `@ControllerAdvice`**

**- \*\*Transactional Integrity\*\* — `@Transactional` ensures ACID compliance**

**- \*\*Clean Architecture\*\* — Layered design with DTOs and separation of concerns**



**---**



**---**



**## 📝 License**



**Open source under the MIT License.**



**## 📞 Contact**



**- \*\*GitHub\*\* — \[@adi270703](https://github.com/adi270703)**

**- \*\*LinkedIn\*\* — \[@itsadityasharma](https://linkedin.com/in/itsadityasharma)**



**---**



**<div align="center">**



**Made with ❤️ by Aditya Sharma | ⭐ Star this project if it helped you!**



**</div>**

