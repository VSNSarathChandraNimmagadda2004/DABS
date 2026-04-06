package com.example.DABS.service;

import com.example.DABS.model.Appointment;
import com.example.DABS.model.Doctor;
import com.example.DABS.model.User;
import com.example.DABS.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getPatientAppointments(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getDoctorAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public Appointment bookAppointment(Appointment appointment) {
        Optional<Appointment> existingAppointment = appointmentRepository.findByDoctorIdAndAppointmentDateAndTimeSlotAndStatusNot(
                appointment.getDoctor().getId(),
                appointment.getAppointmentDate(),
                appointment.getTimeSlot(),
                Appointment.Status.CANCELLED
        );

        if (existingAppointment.isPresent()) {
            throw new RuntimeException("Time slot is already occupied.");
        }

        appointment.setStatus(Appointment.Status.PENDING);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateStatus(Long id, Appointment.Status status) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
}
