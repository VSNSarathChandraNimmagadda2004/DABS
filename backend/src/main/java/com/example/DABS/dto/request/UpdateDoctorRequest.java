package com.example.DABS.dto.request;

public class UpdateDoctorRequest {
    private String specialization;
    private Integer experience;
    private String hospitalName;
    private Double consultationFee;

    public UpdateDoctorRequest() {}

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String s) { this.specialization = s; }
    public Integer getExperience() { return experience; }
    public void setExperience(Integer e) { this.experience = e; }
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String h) { this.hospitalName = h; }
    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double c) { this.consultationFee = c; }
}
