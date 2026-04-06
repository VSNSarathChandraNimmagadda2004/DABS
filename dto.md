
I’ll structure it like this:

* 📦 Package structure
* 📥 Request DTOs
* 📤 Response DTOs
* 🔄 Common DTOs
* ⚠️ Notes for frontend devs

---

# 📦 Package Structure

```
com.hms.dto
│
├── request
├── response
├── common
```

---

# 🔄 Common DTOs (Reusable)

---

## 🧑 UserDTO

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String role;      // PATIENT / DOCTOR / ADMIN
    private String gender;    // MALE / FEMALE / OTHER
    private String phone;
    private String profilePic;
}
```

---

## 🧑‍⚕️ DoctorDTO

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private Long id;
    private UserDTO user;

    private String specialization;
    private Integer experience;
    private String hospitalName;
    private Double consultationFee;

    private Double rating;
}
```

---

## 📅 AvailabilityDTO

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailabilityDTO {

    private String dayOfWeek;   // MONDAY, TUESDAY...
    private String startTime;   // "09:00"
    private String endTime;     // "17:00"
}
```

---

## 📅 AppointmentDTO

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;

    private UserDTO patient;
    private DoctorDTO doctor;

    private String appointmentDate; // "2026-04-10"
    private String timeSlot;        // "10:00-10:30"

    private String status;          // PENDING / CONFIRMED / CANCELLED / COMPLETED
    private String reason;
}
```

---

## 📄 MedicalRecordDTO

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO {

    private Long id;

    private UserDTO patient;
    private DoctorDTO doctor;

    private String diagnosis;
    private String prescriptionUrl;
    private String notes;

    private String createdAt;
}
```

---

# 📥 REQUEST DTOs

---

## 🔐 Create / Update User (after Firebase login)

```java
@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String role;     // PATIENT / DOCTOR / ADMIN
    private String gender;
    private String phone;
    private String profilePic;
}
```

---

## 🧑‍⚕️ Create Doctor Profile

```java
@Data
public class CreateDoctorRequest {

    private String specialization;
    private Integer experience;
    private String hospitalName;
    private Double consultationFee;

    private List<AvailabilityDTO> availability;
}
```

---

## 🧑‍⚕️ Update Doctor

```java
@Data
public class UpdateDoctorRequest {

    private String specialization;
    private Integer experience;
    private String hospitalName;
    private Double consultationFee;
}
```

---

## 📅 Create Appointment

```java
@Data
public class CreateAppointmentRequest {

    private Long doctorId;

    private String appointmentDate;  // "YYYY-MM-DD"
    private String timeSlot;         // "HH:mm-HH:mm"

    private String reason;
}
```

---

## 📅 Update Appointment Status

```java
@Data
public class UpdateAppointmentStatusRequest {

    private String status;   // CONFIRMED / CANCELLED / COMPLETED
}
```

---

## 📄 Create Medical Record

```java
@Data
public class CreateMedicalRecordRequest {

    private Long patientId;
    private Long doctorId;

    private String diagnosis;
    private String prescriptionUrl;
    private String notes;
}
```

---

# 📤 RESPONSE DTOs

---

## ✅ Generic API Response

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
}
```

---

## 🧑 User Response

```java
@Data
public class UserResponse {
    private UserDTO user;
}
```

---

## 🧑‍⚕️ Doctor Response

```java
@Data
public class DoctorResponse {
    private DoctorDTO doctor;
}
```

---

## 🧑‍⚕️ Doctor List Response

```java
@Data
public class DoctorListResponse {

    private List<DoctorDTO> doctors;
}
```

---

## 📅 Appointment Response

```java
@Data
public class AppointmentResponse {

    private AppointmentDTO appointment;
}
```

---

## 📅 Appointment List Response

```java
@Data
public class AppointmentListResponse {

    private List<AppointmentDTO> appointments;
}
```

---

## 📄 Medical Record Response

```java
@Data
public class MedicalRecordResponse {

    private MedicalRecordDTO record;
}
```

---

## 📄 Medical Record List Response

```java
@Data
public class MedicalRecordListResponse {

    private List<MedicalRecordDTO> records;
}
```

---

# 📡 API → DTO Mapping (VERY IMPORTANT FOR TEAM)

---

## 🧑 User

| API               | Request           | Response             |
| ----------------- | ----------------- | -------------------- |
| POST /api/users   | CreateUserRequest | ApiResponse<UserDTO> |
| GET /api/users/me | —                 | ApiResponse<UserDTO> |

---

## 🧑‍⚕️ Doctor

| API                   | Request             | Response           |
| --------------------- | ------------------- | ------------------ |
| POST /api/doctors     | CreateDoctorRequest | DoctorResponse     |
| GET /api/doctors      | —                   | DoctorListResponse |
| GET /api/doctors/{id} | —                   | DoctorResponse     |
| PUT /api/doctors/{id} | UpdateDoctorRequest | DoctorResponse     |

---

## 📅 Appointment

| API                        | Request                        | Response                |
| -------------------------- | ------------------------------ | ----------------------- |
| POST /api/appointments     | CreateAppointmentRequest       | AppointmentResponse     |
| GET /api/appointments      | —                              | AppointmentListResponse |
| PUT /api/appointments/{id} | UpdateAppointmentStatusRequest | AppointmentResponse     |

---

## 📄 Medical Records

| API                          | Request                    | Response                  |
| ---------------------------- | -------------------------- | ------------------------- |
| POST /api/records            | CreateMedicalRecordRequest | MedicalRecordResponse     |
| GET /api/records/{patientId} | —                          | MedicalRecordListResponse |

---

# ⚠️ FRONTEND CONTRACT (VERY IMPORTANT)

Tell your frontend team:

### 1. Dates Format

```
YYYY-MM-DD
```

### 2. Time Slot Format

```
HH:mm-HH:mm
Example: "10:00-10:30"
```

### 3. Enums (STRICT)

```js
role = ["PATIENT", "DOCTOR", "ADMIN"]
gender = ["MALE", "FEMALE", "OTHER"]
status = ["PENDING", "CONFIRMED", "CANCELLED", "COMPLETED"]
dayOfWeek = ["MONDAY", ...]
```

---

# 🚀 Bonus (Highly Useful for You)

Since you're strong in backend + architecture:

👉 Next best steps:

* Add **DTO → Entity mapping using MapStruct**
* Add **Global Exception Handler**
* Add **Pagination DTO (PageResponse)**

---

If you want next:

✅ I can generate:

* Full Controller + Service layer
* Firebase Security Filter
* Postman collection
* OR complete working backend starter

Just tell 👍



