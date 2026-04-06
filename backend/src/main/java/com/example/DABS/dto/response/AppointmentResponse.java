package com.example.DABS.dto.response;

import com.example.DABS.dto.common.AppointmentDTO;
import lombok.Data;
import java.util.List;

@Data
public class AppointmentResponse {
    private AppointmentDTO appointment;
}

@Data
class AppointmentListResponse {
    private List<AppointmentDTO> appointments;
}
