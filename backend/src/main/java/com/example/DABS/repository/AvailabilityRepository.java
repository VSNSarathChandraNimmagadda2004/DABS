package com.example.DABS.repository;

import com.example.DABS.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDoctorId(Long doctorId);
}
