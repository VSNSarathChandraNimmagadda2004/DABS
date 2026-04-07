package com.example.DABS.dto.response;

import com.example.DABS.dto.common.DoctorDTO;
import lombok.Data;
import java.util.List;

@Data
public class DoctorResponse {
    private DoctorDTO doctor;
}

@Data
class DoctorListResponse {
    private List<DoctorDTO> doctors;
}
