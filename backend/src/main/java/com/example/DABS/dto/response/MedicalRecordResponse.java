package com.example.DABS.dto.response;

import com.example.DABS.dto.common.MedicalRecordDTO;
import lombok.Data;
import java.util.List;

@Data
public class MedicalRecordResponse {
    private MedicalRecordDTO record;
}

@Data
class MedicalRecordListResponse {
    private List<MedicalRecordDTO> records;
}
