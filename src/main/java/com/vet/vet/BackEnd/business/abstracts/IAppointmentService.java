package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.AppointmentSaveDTO;
import com.vet.vet.BackEnd.entities.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    Appointment findById(Long id);
    List<Appointment> findAll();
    Boolean save(AppointmentSaveDTO appointmentSaveDTO);
    Boolean update(AppointmentSaveDTO appointmentSaveDTO,Long id);
    Boolean delete(Long id);
    List<Appointment> findAppointmentByDoctorIdAndDate(Long doctorID, LocalDate firstDate, LocalDate secondDate);
    List<Appointment> findAppointmentByAnimalIdAndDate(Long animalID, LocalDate firstDate, LocalDate secondDate);
}
