package com.example.DABS.dto.common;

public class AppointmentDTO {
    private Long id;
    private UserDTO patient;
    private DoctorDTO doctor;
    private String appointmentDate;
    private String timeSlot;
    private String status;
    private String reason;

    public AppointmentDTO() {}

    public AppointmentDTO(Long id, UserDTO patient, DoctorDTO doctor, String appointmentDate, String timeSlot, String status, String reason) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.status = status;
        this.reason = reason;
    }

    public static AppointmentDTOBuilder builder() {
        return new AppointmentDTOBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UserDTO getPatient() { return patient; }
    public void setPatient(UserDTO patient) { this.patient = patient; }
    public DoctorDTO getDoctor() { return doctor; }
    public void setDoctor(DoctorDTO doctor) { this.doctor = doctor; }
    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String d) { this.appointmentDate = d; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String t) { this.timeSlot = t; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public String getReason() { return reason; }
    public void setReason(String r) { this.reason = r; }

    public static class AppointmentDTOBuilder {
        private Long id;
        private UserDTO patient;
        private DoctorDTO doctor;
        private String appointmentDate;
        private String timeSlot;
        private String status;
        private String reason;

        public AppointmentDTOBuilder id(Long i) { this.id = i; return this; }
        public AppointmentDTOBuilder patient(UserDTO p) { this.patient = p; return this; }
        public AppointmentDTOBuilder doctor(DoctorDTO d) { this.doctor = d; return this; }
        public AppointmentDTOBuilder appointmentDate(String d) { this.appointmentDate = d; return this; }
        public AppointmentDTOBuilder timeSlot(String t) { this.timeSlot = t; return this; }
        public AppointmentDTOBuilder status(String s) { this.status = s; return this; }
        public AppointmentDTOBuilder reason(String r) { this.reason = r; return this; }
        public AppointmentDTO build() { return new AppointmentDTO(id, patient, doctor, appointmentDate, timeSlot, status, reason); }
    }
}
