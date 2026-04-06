package com.example.DABS.dto.common;

public class MedicalRecordDTO {
    private Long id;
    private UserDTO patient;
    private DoctorDTO doctor;
    private String diagnosis;
    private String prescriptionUrl;
    private String notes;
    private String createdAt;

    public MedicalRecordDTO() {}

    public MedicalRecordDTO(Long id, UserDTO patient, DoctorDTO doctor, String diagnosis, String prescriptionUrl, String notes, String createdAt) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.prescriptionUrl = prescriptionUrl;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public static MedicalRecordDTOBuilder builder() {
        return new MedicalRecordDTOBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UserDTO getPatient() { return patient; }
    public void setPatient(UserDTO patient) { this.patient = patient; }
    public DoctorDTO getDoctor() { return doctor; }
    public void setDoctor(DoctorDTO doctor) { this.doctor = doctor; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String d) { this.diagnosis = d; }
    public String getPrescriptionUrl() { return prescriptionUrl; }
    public void setPrescriptionUrl(String p) { this.prescriptionUrl = p; }
    public String getNotes() { return notes; }
    public void setNotes(String n) { this.notes = n; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String c) { this.createdAt = c; }

    public static class MedicalRecordDTOBuilder {
        private Long id;
        private UserDTO patient;
        private DoctorDTO doctor;
        private String diagnosis;
        private String prescriptionUrl;
        private String notes;
        private String createdAt;

        public MedicalRecordDTOBuilder id(Long i) { this.id = i; return this; }
        public MedicalRecordDTOBuilder patient(UserDTO p) { this.patient = p; return this; }
        public MedicalRecordDTOBuilder doctor(DoctorDTO d) { this.doctor = d; return this; }
        public MedicalRecordDTOBuilder diagnosis(String d) { this.diagnosis = d; return this; }
        public MedicalRecordDTOBuilder prescriptionUrl(String p) { this.prescriptionUrl = p; return this; }
        public MedicalRecordDTOBuilder notes(String n) { this.notes = n; return this; }
        public MedicalRecordDTOBuilder createdAt(String c) { this.createdAt = c; return this; }
        public MedicalRecordDTO build() { return new MedicalRecordDTO(id, patient, doctor, diagnosis, prescriptionUrl, notes, createdAt); }
    }
}
