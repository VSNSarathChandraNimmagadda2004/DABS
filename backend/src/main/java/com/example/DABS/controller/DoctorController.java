package com.example.DABS.controller;

import com.example.DABS.dto.common.DoctorDTO;
import com.example.DABS.dto.common.UserDTO;
import com.example.DABS.dto.common.AvailabilityDTO;
import com.example.DABS.dto.request.CreateDoctorRequest;
import com.example.DABS.dto.response.ApiResponse;
import com.example.DABS.model.Doctor;
import com.example.DABS.model.Availability;
import com.example.DABS.model.User;
import com.example.DABS.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorDTO>> registerDoctor(@RequestBody CreateDoctorRequest request) {
        Doctor doctor = doctorService.registerDoctor(request);
        return ResponseEntity.ok(ApiResponse.<DoctorDTO>builder()
                .success(true)
                .message("Doctor profile registered successfully")
                .data(convertToDTO(doctor))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorDTO>>> getAllDoctors(@RequestParam(required = false) String specialization) {
        List<Doctor> doctors;
        if (specialization != null) {
            doctors = doctorService.searchDoctorsBySpecialization(specialization);
        } else {
            doctors = doctorService.getAllDoctors();
        }
        
        List<DoctorDTO> dtos = doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.<List<DoctorDTO>>builder()
                .success(true)
                .message("Doctors retrieved successfully")
                .data(dtos)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorDTO>> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(ApiResponse.<DoctorDTO>builder()
                .success(true)
                .message("Doctor retrieved successfully")
                .data(convertToDTO(doctor))
                .build());
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<List<AvailabilityDTO>>> getDoctorAvailability(@PathVariable Long id) {
        List<Availability> availability = doctorService.getAvailability(id);
        List<AvailabilityDTO> dtos = availability.stream()
                .map(a -> AvailabilityDTO.builder()
                        .dayOfWeek(a.getDayOfWeek().name())
                        .startTime(a.getStartTime().toString())
                        .endTime(a.getEndTime().toString())
                        .build())
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(ApiResponse.<List<AvailabilityDTO>>builder()
                .success(true)
                .message("Availability retrieved successfully")
                .data(dtos)
                .build());
    }

    private DoctorDTO convertToDTO(Doctor doctor) {
        User user = doctor.getUser();
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .gender(user.getGender().name())
                .phone(user.getPhone())
                .profilePic(user.getProfilePic())
                .build();

        return DoctorDTO.builder()
                .id(doctor.getId())
                .user(userDTO)
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .hospitalName(doctor.getHospitalName())
                .consultationFee(doctor.getConsultationFee().doubleValue())
                .rating(doctor.getRating())
                .build();
    }
}
