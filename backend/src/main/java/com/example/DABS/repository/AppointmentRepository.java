package com.example.DABS.repository;

import com.example.DABS.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    Optional<Appointment> findByDoctorIdAndAppointmentDateAndTimeSlotAndStatusNot(
        Long doctorId, LocalDate appointmentDate, String timeSlot, Appointment.Status status);
}
