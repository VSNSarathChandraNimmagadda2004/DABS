package com.example.DABS.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "time_slot")
    private String timeSlot;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String reason;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Appointment() {
        this.status = Status.PENDING;
    }

    public Appointment(Long id, User patient, Doctor doctor, LocalDate appointmentDate, String timeSlot, Status status, String reason) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.status = status != null ? status : Status.PENDING;
        this.reason = reason;
    }

    public static AppointmentBuilder builder() {
        return new AppointmentBuilder();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getPatient() { return patient; }
    public void setPatient(User p) { this.patient = p; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor d) { this.doctor = d; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate d) { this.appointmentDate = d; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String t) { this.timeSlot = t; }
    public Status getStatus() { return status; }
    public void setStatus(Status s) { this.status = s; }
    public String getReason() { return reason; }
    public void setReason(String r) { this.reason = r; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum Status { PENDING, CONFIRMED, CANCELLED, COMPLETED }

    public static class AppointmentBuilder {
        private Long id;
        private User patient;
        private Doctor doctor;
        private LocalDate appointmentDate;
        private String timeSlot;
        private Status status;
        private String reason;

        public AppointmentBuilder id(Long i) { this.id = i; return this; }
        public AppointmentBuilder patient(User p) { this.patient = p; return this; }
        public AppointmentBuilder doctor(Doctor d) { this.doctor = d; return this; }
        public AppointmentBuilder appointmentDate(LocalDate d) { this.appointmentDate = d; return this; }
        public AppointmentBuilder timeSlot(String t) { this.timeSlot = t; return this; }
        public AppointmentBuilder status(Status s) { this.status = s; return this; }
        public AppointmentBuilder reason(String r) { this.reason = r; return this; }
        public Appointment build() { return new Appointment(id, patient, doctor, appointmentDate, timeSlot, status, reason); }
    }
}
