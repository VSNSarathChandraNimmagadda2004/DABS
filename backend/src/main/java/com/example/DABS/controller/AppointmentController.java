package com.example.DABS.controller;

import com.example.DABS.dto.common.AppointmentDTO;
import com.example.DABS.dto.common.DoctorDTO;
import com.example.DABS.dto.common.UserDTO;
import com.example.DABS.dto.request.CreateAppointmentRequest;
import com.example.DABS.dto.request.UpdateAppointmentStatusRequest;
import com.example.DABS.dto.response.ApiResponse;
import com.example.DABS.model.Appointment;
import com.example.DABS.model.Doctor;
import com.example.DABS.model.User;
import com.example.DABS.service.AppointmentService;
import com.example.DABS.service.DoctorService;
import com.example.DABS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> getMyAppointments() {
        User user = userService.getOrCreateUser();
        List<Appointment> appointments;
        if (user.getRole() == User.Role.DOCTOR) {
            appointments = appointmentService.getDoctorAppointments(user.getId());
        } else {
            appointments = appointmentService.getPatientAppointments(user.getId());
        }
        
        List<AppointmentDTO> dtos = appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.<List<AppointmentDTO>>builder()
                .success(true)
                .message("Appointments retrieved successfully")
                .data(dtos)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentDTO>> bookAppointment(@RequestBody CreateAppointmentRequest request) {
        User patient = userService.getOrCreateUser();
        Doctor doctor = doctorService.getDoctorById(request.getDoctorId());

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .appointmentDate(java.time.LocalDate.parse(request.getAppointmentDate()))
                .timeSlot(request.getTimeSlot())
                .reason(request.getReason())
                .build();

        Appointment saved = appointmentService.bookAppointment(appointment);
        return ResponseEntity.ok(ApiResponse.<AppointmentDTO>builder()
                .success(true)
                .message("Appointment booked successfully")
                .data(convertToDTO(saved))
                .build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AppointmentDTO>> updateStatus(@PathVariable Long id, @RequestBody UpdateAppointmentStatusRequest request) {
        Appointment updated = appointmentService.updateStatus(id, Appointment.Status.valueOf(request.getStatus()));
        return ResponseEntity.ok(ApiResponse.<AppointmentDTO>builder()
                .success(true)
                .message("Appointment status updated")
                .data(convertToDTO(updated))
                .build());
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .patient(convertToUserDTO(appointment.getPatient()))
                .doctor(convertToDoctorDTO(appointment.getDoctor()))
                .appointmentDate(appointment.getAppointmentDate().toString())
                .timeSlot(appointment.getTimeSlot())
                .status(appointment.getStatus().name())
                .reason(appointment.getReason())
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
