package com.example.DABS.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String specialization;
    
    private Integer experience;
    
    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "consultation_fee")
    private java.math.BigDecimal consultationFee;

    private Double rating;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Availability> availability = new java.util.ArrayList<>();

    public Doctor() {}

    public Doctor(Long id, User user, String specialization, Integer experience, String hospitalName, java.math.BigDecimal consultationFee, Double rating) {
        this.id = id;
        this.user = user;
        this.specialization = specialization;
        this.experience = experience;
        this.hospitalName = hospitalName;
        this.consultationFee = consultationFee;
        this.rating = rating;
    }

    public static DoctorBuilder builder() {
        return new DoctorBuilder();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String s) { this.specialization = s; }
    public Integer getExperience() { return experience; }
    public void setExperience(Integer e) { this.experience = e; }
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String h) { this.hospitalName = h; }
    public java.math.BigDecimal getConsultationFee() { return consultationFee; }
    public void setConsultationFee(java.math.BigDecimal c) { this.consultationFee = c; }
    public Double getRating() { return rating != null ? rating : 0.0; }
    public void setRating(Double r) { this.rating = r; }
    public java.util.List<Availability> getAvailability() { return availability; }
    public void setAvailability(java.util.List<Availability> a) { this.availability = a; }

    public static class DoctorBuilder {
        private Long id;
        private User user;
        private String specialization;
        private Integer experience;
        private String hospitalName;
        private java.math.BigDecimal consultationFee;
        private Double rating;

        public DoctorBuilder id(Long i) { this.id = i; return this; }
        public DoctorBuilder user(User u) { this.user = u; return this; }
        public DoctorBuilder specialization(String s) { this.specialization = s; return this; }
        public DoctorBuilder experience(Integer e) { this.experience = e; return this; }
        public DoctorBuilder hospitalName(String h) { this.hospitalName = h; return this; }
        public DoctorBuilder consultationFee(java.math.BigDecimal c) { this.consultationFee = c; return this; }
        public DoctorBuilder rating(Double r) { this.rating = r; return this; }
        public Doctor build() { return new Doctor(id, user, specialization, experience, hospitalName, consultationFee, rating); }
    }
}
