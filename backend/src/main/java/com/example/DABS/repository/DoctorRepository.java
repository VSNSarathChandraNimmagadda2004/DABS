package com.example.DABS.repository;

import com.example.DABS.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);
    boolean existsByUserId(Long userId);
}
