package com.example.DABS.service;

import com.example.DABS.dto.request.CreateDoctorRequest;
import com.example.DABS.model.Doctor;
import com.example.DABS.model.User;
import com.example.DABS.model.Availability;
import com.example.DABS.repository.DoctorRepository;
import com.example.DABS.repository.AvailabilityRepository;
import com.example.DABS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AvailabilityRepository availabilityRepository;
    private final UserRepository userRepository;

    public void ensureDoctorExists(User user) {
        if (!doctorRepository.existsByUserId(user.getId())) {
            Doctor doctor = Doctor.builder()
                    .user(user)
                    .specialization("General Physician") // Default
                    .hospitalName("DABS Hospital")       // Default
                    .consultationFee(java.math.BigDecimal.valueOf(500.0))
                    .experience(0)
                    .build();
            doctorRepository.save(doctor);
        }
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> searchDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationContainingIgnoreCase(specialization);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor registerDoctor(CreateDoctorRequest request) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String firebaseUid = jwt.getSubject();
        
        User user = userRepository.findByFirebaseUid(firebaseUid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(User.Role.DOCTOR);
        userRepository.save(user);
        
        Doctor doctor = Doctor.builder()
                .user(user)
                .specialization(request.getSpecialization())
                .experience(request.getExperience())
                .hospitalName(request.getHospitalName())
                .consultationFee(BigDecimal.valueOf(request.getConsultationFee()))
                .build();

        Doctor savedDoctor = doctorRepository.save(doctor);

        if (request.getAvailability() != null) {
            List<Availability> availabilityList = request.getAvailability().stream()
                    .map(dto -> Availability.builder()
                            .doctor(savedDoctor)
                            .dayOfWeek(Availability.DayOfWeek.valueOf(dto.getDayOfWeek()))
                            .startTime(LocalTime.parse(dto.getStartTime()))
                            .endTime(LocalTime.parse(dto.getEndTime()))
                            .build())
                    .collect(Collectors.toList());
            availabilityRepository.saveAll(availabilityList);
        }

        return savedDoctor;
    }

    public List<Availability> getAvailability(Long doctorId) {
        return availabilityRepository.findByDoctorId(doctorId);
    }

    public void setAvailability(Long doctorId, List<Availability> availabilityList) {
        Doctor doctor = getDoctorById(doctorId);
        availabilityList.forEach(availability -> availability.setDoctor(doctor));
        availabilityRepository.saveAll(availabilityList);
    }
}
