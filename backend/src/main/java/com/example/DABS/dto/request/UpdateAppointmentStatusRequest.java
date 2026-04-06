package com.example.DABS.dto.request;

public class UpdateAppointmentStatusRequest {
    private String status;

    public UpdateAppointmentStatusRequest() {}
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
}
