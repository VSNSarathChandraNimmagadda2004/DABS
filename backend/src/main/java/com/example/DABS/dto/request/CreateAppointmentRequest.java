package com.example.DABS.dto.request;

public class CreateAppointmentRequest {
    private Long doctorId;
    private String appointmentDate;
    private String timeSlot;
    private String reason;

    public CreateAppointmentRequest() {}

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long id) { this.doctorId = id; }
    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String d) { this.appointmentDate = d; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String t) { this.timeSlot = t; }
    public String getReason() { return reason; }
    public void setReason(String r) { this.reason = r; }
}
