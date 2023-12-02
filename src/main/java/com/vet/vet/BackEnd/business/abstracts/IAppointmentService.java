package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.AppointmentSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.AppointmentUpdateDTO;
import com.vet.vet.BackEnd.entities.Appointment;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    ResultData<Appointment> findById(Long id);
    ResultData<List<Appointment>> findAll();
    Result save(AppointmentSaveDTO appointmentSaveDTO);
    Result update(AppointmentUpdateDTO appointmentUpdateDTO, Long id);
    Result delete(Long id);
    ResultData<List<Appointment>> findAppointmentByDoctorIdAndDate(Long doctorID, LocalDate firstDate, LocalDate secondDate);
    ResultData<List<Appointment>> findAppointmentByAnimalIdAndDate(Long animalID, LocalDate firstDate, LocalDate secondDate);
}
