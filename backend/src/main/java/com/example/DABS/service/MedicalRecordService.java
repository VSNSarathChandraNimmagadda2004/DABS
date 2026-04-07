package com.example.DABS.service;

import com.example.DABS.model.MedicalRecord;
import com.example.DABS.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getPatientRecords(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    public MedicalRecord addMedicalRecord(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }
}
