package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAppointmentService;
import com.vet.vet.BackEnd.dao.AppointmentRepository;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import com.vet.vet.BackEnd.dto.requestDto.AppointmentSaveDTO;
import com.vet.vet.BackEnd.entities.Appointment;
import com.vet.vet.BackEnd.entities.AvailableDate;
import com.vet.vet.BackEnd.entities.Doctor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentManager(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Appointment findById(Long id) {
        if (this.appointmentRepository.existsById(id)){
            return this.appointmentRepository.findById(id).orElseThrow();
        }
        return null;
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public Boolean save(AppointmentSaveDTO appointmentSaveDTO) {

        if (this.doctorRepository.existsById(appointmentSaveDTO.getDoctorID())){
            Doctor doctor = this.doctorRepository.findById(appointmentSaveDTO.getDoctorID()).orElseThrow();
            List<AvailableDate> doctorWorkDays = doctor.getAvailableDates();
            AvailableDate wantedDay = null;

            wantedDay = doctorWorkDays.stream().filter(availableDate -> availableDate.getAvailableDate().equals(appointmentSaveDTO.getAppointmentDate().toLocalDate())).findFirst().orElse(null);



            System.out.println(wantedDay);

            if (wantedDay == null){
                // doktor o gün çalışmıyor
            }else{
                // O gün çalışıyor
                // şimdi o günün istenen saatinde müsait mi ona bak
            }

        }







        return null;
    }

    @Override
    public Boolean update(AppointmentSaveDTO appointmentSaveDTO,Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Boolean result = false;
        if (this.appointmentRepository.existsById(id)){
            this.appointmentRepository.deleteById(id);
            result = true;
        }

        return result;
    }

    @Override
    public List<Appointment> findAppointmentByDoctorIdAndDate(Long doctorID, LocalDate firstDate, LocalDate secondDate) {
        return null;
    }

    @Override
    public List<Appointment> findAppointmentByAnimalIdAndDate(Long animalID, LocalDate firstDate, LocalDate secondDate) {
        return null;
    }
}
