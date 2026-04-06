# DABS
This project is solely based on appointment booking system for patients and doctors


Got it — I’ll rewrite everything cleanly for your **exact stack** and format it as a **GLOBAL README (production-level)** that your whole team can follow.

---

# 🏥 Hospital Management System (Global README)

---

## 🧠 Core Idea

A full-stack Hospital Management System where:

* Patients can search doctors and book appointments.
* Doctors manage schedules and patient records.
* Admin oversees the system.
* Firebase handles authentication.
* Spring Boot handles business logic securely.

---

## 🧩 Tech Stack

### Frontend

* React.js (with normal CSS)
* fetch api (API calls)
* React Router

### Backend

* Spring Boot (Java)
* Spring Security (for Firebase token validation)
* REST APIs

### Database

* MySQL (via Spring Data JPA / Hibernate)

### Authentication

* Firebase Authentication (Email/Password + Google)

### Hosting

* Frontend → Vercel / Netlify
* Backend → Render / Railway / AWS
* Database → MySQL (AWS RDS / PlanetScale / local)

---

## 🔐 Authentication Flow

1. User logs in via Firebase (frontend)
2. Firebase returns **ID Token**
3. Token is sent in headers:

   ```
   Authorization: Bearer <firebase_token>
   ```
4. Spring Boot verifies token using Firebase Admin SDK
5. Backend extracts user details (UID, email)

---

## 🧑‍⚕️ User Roles

* **PATIENT**
* **DOCTOR**
* **ADMIN**

👉 Stored in database (NOT Firebase)

---

## 🧩 Features

### 1. Patient

* Search doctors
* Book appointments
* View history
* Upload medical records

### 2. Doctor

* Set availability
* Manage appointments
* Add prescriptions

### 3. Admin

* Manage users
* Monitor system

---

## ⚙️ Backend APIs

### Auth (handled via Firebase middleware)

* No login endpoint required

---

### Doctor APIs

```
POST   /api/doctors
GET    /api/doctors
GET    /api/doctors/{id}
PUT    /api/doctors/{id}
DELETE /api/doctors/{id}
```

---

### Appointment APIs

```
POST   /api/appointments
GET    /api/appointments
GET    /api/appointments/{id}
PUT    /api/appointments/{id}
DELETE /api/appointments/{id}
```

---

### Medical Records APIs

```
POST   /api/records
GET    /api/records/{patientId}
```

---

### User APIs

```
GET /api/users/me
```

---

## 💾 Database Schema (MySQL + JPA)

---

### 🧑 User Table

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    firebase_uid VARCHAR(128) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,

    role ENUM('PATIENT', 'DOCTOR', 'ADMIN') NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,

    phone VARCHAR(15),
    profile_pic TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### 🧑‍⚕️ Doctor Table

```sql
CREATE TABLE doctors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    user_id BIGINT NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    experience INT,
    hospital_name VARCHAR(150),
    consultation_fee DECIMAL(10,2),

    rating DOUBLE DEFAULT 0,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

---

### 📅 Availability Table (IMPORTANT DESIGN)

```sql
CREATE TABLE availability (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    doctor_id BIGINT NOT NULL,
    day_of_week ENUM('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY'),

    start_time TIME,
    end_time TIME,

    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);
```

---

### 📅 Appointment Table

```sql
CREATE TABLE appointments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,

    appointment_date DATE NOT NULL,
    time_slot VARCHAR(50) NOT NULL,

    status ENUM('PENDING','CONFIRMED','CANCELLED','COMPLETED') DEFAULT 'PENDING',

    reason TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (patient_id) REFERENCES users(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
```

---

### 📄 Medical Records Table

```sql
CREATE TABLE medical_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,

    diagnosis TEXT,
    prescription_url TEXT,
    notes TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (patient_id) REFERENCES users(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
```

---

## 🧠 Backend Architecture (Spring Boot)

```
com.hms
│
├── controller
├── service
├── repository
├── model (Entity classes)
├── dto
├── config
├── security (Firebase filter)
└── exception
```

---

## 🔐 Firebase Integration (Spring Boot)

### Dependency

```xml
<dependency>
  <groupId>com.google.firebase</groupId>
  <artifactId>firebase-admin</artifactId>
</dependency>
```

---

### Token Filter Flow

* Extract token from header
* Verify using Firebase Admin SDK
* Set authentication in Spring Security context

---

## 🛡️ Constraints (CRITICAL)

* ❌ No overlapping appointments
* ✅ Validate availability before booking
* ✅ Only authenticated users allowed
* ✅ Role-based access
* ✅ Prevent double booking

---

## 🎨 Frontend Pages (React)

* Landing Page
* Login / Signup
* Home (Doctor Search)
* Doctor Details
* Book Appointment
* Dashboard (Patient / Doctor / Admin)
* Medical Records

---

## 📂 Folder Structure

```
project-root/
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/ (API calls)
│   │   ├── context/ (auth context)
│
├── backend/
│   ├── src/main/java/com/hms/
│   ├── resources/
│
└── README.md
```

---

## ⚙️ Setup Instructions

---

### Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
```

---

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hms
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### Frontend (React)

```bash
cd frontend
npm install
npm start
```

---

### Firebase Setup

* Enable:

  * Email/Password
  * Google Login
* Download service account key → backend

---

## 📡 API Example

### Create Appointment

```
POST /api/appointments

Headers:
Authorization: Bearer <firebase_token>

Body:
{
  "doctorId": 1,
  "appointmentDate": "2026-04-10",
  "timeSlot": "10:00-10:30",
  "reason": "Fever"
}
```

---

## 🚀 Future Enhancements

* Video consultation (WebRTC)
* AI disease prediction (you can plug your glaucoma model 🔥)
* Payments (Razorpay)
* Doctor ratings
* Notifications (Firebase Cloud Messaging)

---

