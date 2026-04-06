package com.example.DABS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "availabilities")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private java.time.LocalTime startTime;
    private java.time.LocalTime endTime;

    public Availability() {}

    public Availability(Long id, Doctor doctor, DayOfWeek dayOfWeek, java.time.LocalTime startTime, java.time.LocalTime endTime) {
        this.id = id;
        this.doctor = doctor;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static AvailabilityBuilder builder() {
        return new AvailabilityBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor d) { this.doctor = d; }
    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek d) { this.dayOfWeek = d; }
    public java.time.LocalTime getStartTime() { return startTime; }
    public void setStartTime(java.time.LocalTime s) { this.startTime = s; }
    public java.time.LocalTime getEndTime() { return endTime; }
    public void setEndTime(java.time.LocalTime e) { this.endTime = e; }

    public enum DayOfWeek { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    public static class AvailabilityBuilder {
        private Long id;
        private Doctor doctor;
        private DayOfWeek dayOfWeek;
        private java.time.LocalTime startTime;
        private java.time.LocalTime endTime;

        public AvailabilityBuilder id(Long i) { this.id = i; return this; }
        public AvailabilityBuilder doctor(Doctor d) { this.doctor = d; return this; }
        public AvailabilityBuilder dayOfWeek(DayOfWeek d) { this.dayOfWeek = d; return this; }
        public AvailabilityBuilder startTime(java.time.LocalTime s) { this.startTime = s; return this; }
        public AvailabilityBuilder endTime(java.time.LocalTime e) { this.endTime = e; return this; }
        public Availability build() { return new Availability(id, doctor, dayOfWeek, startTime, endTime); }
    }
}
