package com.example.DABS.dto.request;

public class CreateMedicalRecordRequest {
    private Long patientId;
    private Long doctorId;
    private String diagnosis;
    private String prescriptionUrl;
    private String notes;

    public CreateMedicalRecordRequest() {}

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long id) { this.patientId = id; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long id) { this.doctorId = id; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String d) { this.diagnosis = d; }
    public String getPrescriptionUrl() { return prescriptionUrl; }
    public void setPrescriptionUrl(String p) { this.prescriptionUrl = p; }
    public String getNotes() { return notes; }
    public void setNotes(String n) { this.notes = n; }
}
