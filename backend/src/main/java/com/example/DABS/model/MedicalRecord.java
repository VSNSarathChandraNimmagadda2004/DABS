package com.example.DABS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(name = "prescription_url")
    private String prescriptionUrl;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    public MedicalRecord() {}

    public MedicalRecord(Long id, User patient, Doctor doctor, String diagnosis, String prescriptionUrl, String notes, java.time.LocalDateTime createdAt) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.prescriptionUrl = prescriptionUrl;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public static MedicalRecordBuilder builder() {
        return new MedicalRecordBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getPatient() { return patient; }
    public void setPatient(User p) { this.patient = p; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor d) { this.doctor = d; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String d) { this.diagnosis = d; }

    public String getPrescriptionUrl() {
        return prescriptionUrl;
    }

    public void setPrescriptionUrl(String p) {
        this.prescriptionUrl = p;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String n) {
        this.notes = n;
    }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
    }

    public static class MedicalRecordBuilder {
        private Long id;
        private User patient;
        private Doctor doctor;
        private String diagnosis;
        private String prescriptionUrl;
        private String notes;
        private java.time.LocalDateTime createdAt;

        public MedicalRecordBuilder id(Long i) { this.id = i; return this; }
        public MedicalRecordBuilder patient(User p) { this.patient = p; return this; }
        public MedicalRecordBuilder doctor(Doctor d) { this.doctor = d; return this; }
        public MedicalRecordBuilder diagnosis(String d) { this.diagnosis = d; return this; }
        public MedicalRecordBuilder prescriptionUrl(String p) { this.prescriptionUrl = p; return this; }
        public MedicalRecordBuilder notes(String n) { this.notes = n; return this; }
        public MedicalRecordBuilder createdAt(java.time.LocalDateTime c) { this.createdAt = c; return this; }
        public MedicalRecord build() { return new MedicalRecord(id, patient, doctor, diagnosis, prescriptionUrl, notes, createdAt); }
    }
}
