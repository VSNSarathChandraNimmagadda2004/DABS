package com.example.DABS.dto.request;

import com.example.DABS.dto.common.AvailabilityDTO;
import java.util.List;

public class CreateDoctorRequest {
    private String specialization;
    private Integer experience;
    private String hospitalName;
    private Double consultationFee;
    private List<AvailabilityDTO> availability;

    public CreateDoctorRequest() {}

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }
    public List<AvailabilityDTO> getAvailability() { return availability; }
    public void setAvailability(List<AvailabilityDTO> availability) { this.availability = availability; }
}
