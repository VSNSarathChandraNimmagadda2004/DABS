package com.example.DABS.dto.common;

public class DoctorDTO {
    private Long id;
    private UserDTO user;
    private String specialization;
    private Integer experience;
    private String hospitalName;
    private Double consultationFee;
    private Double rating;

    public DoctorDTO() {}

    public DoctorDTO(Long id, UserDTO user, String specialization, Integer experience, String hospitalName, Double consultationFee, Double rating) {
        this.id = id;
        this.user = user;
        this.specialization = specialization;
        this.experience = experience;
        this.hospitalName = hospitalName;
        this.consultationFee = consultationFee;
        this.rating = rating;
    }

    public static DoctorDTOBuilder builder() {
        return new DoctorDTOBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public static class DoctorDTOBuilder {
        private Long id;
        private UserDTO user;
        private String specialization;
        private Integer experience;
        private String hospitalName;
        private Double consultationFee;
        private Double rating;

        public DoctorDTOBuilder id(Long id) { this.id = id; return this; }
        public DoctorDTOBuilder user(UserDTO user) { this.user = user; return this; }
        public DoctorDTOBuilder specialization(String s) { this.specialization = s; return this; }
        public DoctorDTOBuilder experience(Integer e) { this.experience = e; return this; }
        public DoctorDTOBuilder hospitalName(String h) { this.hospitalName = h; return this; }
        public DoctorDTOBuilder consultationFee(Double c) { this.consultationFee = c; return this; }
        public DoctorDTOBuilder rating(Double r) { this.rating = r; return this; }
        public DoctorDTO build() { return new DoctorDTO(id, user, specialization, experience, hospitalName, consultationFee, rating); }
    }
}
