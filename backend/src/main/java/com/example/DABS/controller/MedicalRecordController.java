package com.example.DABS.controller;

import com.example.DABS.dto.common.MedicalRecordDTO;
import com.example.DABS.dto.common.DoctorDTO;
import com.example.DABS.dto.common.UserDTO;
import com.example.DABS.dto.request.CreateMedicalRecordRequest;
import com.example.DABS.dto.response.ApiResponse;
import com.example.DABS.model.Doctor;
import com.example.DABS.model.MedicalRecord;
import com.example.DABS.model.User;
import com.example.DABS.service.DoctorService;
import com.example.DABS.service.MedicalRecordService;
import com.example.DABS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final UserService userService;
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MedicalRecordDTO>>> getMyRecords() {
        User user = userService.getOrCreateUser();
        List<MedicalRecord> records = medicalRecordService.getPatientRecords(user.getId());
        List<MedicalRecordDTO> dtos = records.stream().map(this::convertToDTO).collect(Collectors.toList());
        
        return ResponseEntity.ok(ApiResponse.<List<MedicalRecordDTO>>builder()
                .success(true)
                .message("Medical records retrieved")
                .data(dtos)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MedicalRecordDTO>> addRecord(@RequestBody CreateMedicalRecordRequest request) {
        User patient = userService.getUserById(request.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorService.getDoctorById(request.getDoctorId());

        MedicalRecord record = MedicalRecord.builder()
                .patient(patient)
                .doctor(doctor)
                .diagnosis(request.getDiagnosis())
                .prescriptionUrl(request.getPrescriptionUrl())
                .notes(request.getNotes())
                .build();

        MedicalRecord saved = medicalRecordService.addMedicalRecord(record);
        return ResponseEntity.ok(ApiResponse.<MedicalRecordDTO>builder()
                .success(true)
                .message("Medical record added successfully")
                .data(convertToDTO(saved))
                .build());
    }

    private MedicalRecordDTO convertToDTO(MedicalRecord record) {
        return MedicalRecordDTO.builder()
                .id(record.getId())
                .patient(convertToUserDTO(record.getPatient()))
                .doctor(convertToDoctorDTO(record.getDoctor()))
                .diagnosis(record.getDiagnosis())
                .prescriptionUrl(record.getPrescriptionUrl())
                .notes(record.getNotes())
                .createdAt(record.getCreatedAt().toString())
                .build();
    }

    private UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .gender(user.getGender().name())
                .phone(user.getPhone())
                .profilePic(user.getProfilePic())
                .build();
    }

    private DoctorDTO convertToDoctorDTO(Doctor doctor) {
        return DoctorDTO.builder()
                .id(doctor.getId())
                .user(convertToUserDTO(doctor.getUser()))
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .hospitalName(doctor.getHospitalName())
                .consultationFee(doctor.getConsultationFee().doubleValue())
                .rating(doctor.getRating())
                .build();
    }
}
