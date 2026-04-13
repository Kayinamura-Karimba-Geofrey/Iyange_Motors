# 🚗 IYANGE MOTORS

### Vehicle Rental Management System
**Backend Design using Spring Boot**

---

## 📌 1. PROJECT OVERVIEW
**Iyange Motors** is a mobile-based vehicle rental management system that allows users to browse, book, and rent vehicles, while administrators manage vehicles, bookings, users, and payments.

---

## 🎯 2. OBJECTIVES
* Provide a seamless vehicle rental experience
* Enable real-time booking and availability tracking
* Ensure secure authentication and authorization
* Maintain scalable and clean backend architecture

---

## 🏗️ 3. SYSTEM ARCHITECTURE
### 🔹 Architecture Pattern
* Layered Architecture:
  * Controller Layer (REST APIs)
  * Service Layer (Business Logic)
  * Repository Layer (Data Access)
  * Model Layer (Entities)
  * DTO Layer (Data Transfer)

---

## ⚙️ 4. TECHNOLOGY STACK
### Backend
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Spring Security** (JWT Authentication)

### Database
* **PostgreSQL**

### Tools
* **Maven**
* **Postman** (API Testing)
* **Git & GitHub**

---

## 👥 5. USER ROLES
### 1. CUSTOMER
* Register/Login
* Browse vehicles
* Book vehicles
* Make payments
* View rental history

### 2. ADMIN
* Manage vehicles
* Manage bookings
* Manage users
* View reports

---

## 🔐 6. AUTHENTICATION & SECURITY
* JWT-based authentication
* Password encryption using BCrypt
* Role-based authorization
* Secure API endpoints

---

## 📦 7. PROJECT STRUCTURE
```
com.iyangemotors
│
├── controllers
├── services
│   ├── interfaces
│   └── implementations
├── repositories
├── models
├── dtos
├── enums
├── security
├── config
└── exceptions
```

---

## 🧩 8. DATABASE DESIGN

### 👤 USERS TABLE
```sql
id (UUID, PK)
full_name
email (unique)
phone
password
role (CUSTOMER, ADMIN)
driver_license
created_at
```

### 🚗 VEHICLES TABLE
```sql
id (UUID, PK)
name
brand
model
type
price_per_day
availability
description
created_at
```

### 🖼️ VEHICLE_IMAGES TABLE
```sql
id (UUID, PK)
vehicle_id (FK)
image_url
```

### 📅 BOOKINGS TABLE
```sql
id (UUID, PK)
user_id (FK)
vehicle_id (FK)
start_date
end_date
total_price
status (PENDING, APPROVED, ONGOING, COMPLETED, CANCELLED)
created_at
```

### 💳 PAYMENTS TABLE
```sql
id (UUID, PK)
booking_id (FK)
amount
payment_method (MOMO, CARD)
payment_status (SUCCESS, FAILED)
transaction_ref
created_at
```

### 🚘 RENTALS TABLE
```sql
id (UUID, PK)
booking_id (FK)
pickup_location
return_location
actual_pickup_time
actual_return_time
status
```

### ⭐ REVIEWS TABLE
```sql
id (UUID, PK)
user_id (FK)
vehicle_id (FK)
rating
comment
created_at
```

---

## 🔗 9. ENTITY RELATIONSHIPS
* One User → Many Bookings
* One Vehicle → Many Bookings
* One Booking → One Payment
* One Booking → One Rental
* One Vehicle → Many Images
* Users & Vehicles → Reviews

---

## 🔄 10. SYSTEM WORKFLOW
### Booking Flow
1. User selects vehicle
2. Chooses rental dates
3. System calculates price
4. Booking created (PENDING)
5. Admin approves booking
6. Payment is made
7. Rental begins (ONGOING)
8. Rental completed

---

## 🧠 11. BUSINESS LOGIC RULES
* Prevent double booking (date validation)
* Only available vehicles can be booked
* Payment must be completed before rental starts
* Booking status must follow proper lifecycle

---

## 📡 12. API ENDPOINTS (EXAMPLES)

### AUTH
* `POST /api/auth/register`
* `POST /api/auth/login`

### VEHICLES
* `GET /api/vehicles`
* `POST /api/vehicles`
* `PUT /api/vehicles/{id}`
* `DELETE /api/vehicles/{id}`

### BOOKINGS
* `POST /api/bookings`
* `GET /api/bookings/user/{id}`
* `PUT /api/bookings/{id}/approve`

### PAYMENTS
* `POST /api/payments`
* `GET /api/payments/{bookingId}`

---

## 🚀 13. ADVANCED FEATURES (OPTIONAL)
* Push notifications
* GPS tracking
* Dynamic pricing
* Admin analytics dashboard

---

## 🧪 14. TESTING
* Unit Testing (JUnit)
* API Testing (Postman)
* Integration Testing

---

## 📈 15. SCALABILITY & IMPROVEMENTS
* Microservices architecture (future)
* Docker containerization
* Cloud deployment (AWS, Azure)

---

## ✅ 16. CONCLUSION
The **Iyange Motors** system is designed to be scalable, secure, and production-ready, following modern Spring Boot best practices.
