package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAppointmentService;
import com.vet.vet.BackEnd.dao.AnimalRepository;
import com.vet.vet.BackEnd.dao.AppointmentRepository;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import com.vet.vet.BackEnd.dto.requestDto.AppointmentSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.AppointmentUpdateDTO;
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
    private final AnimalRepository animalRepository;

    public AppointmentManager(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, AnimalRepository animalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
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
        Boolean result = false;

        if (this.doctorRepository.existsById(appointmentSaveDTO.getDoctorID())){
            if (this.animalRepository.existsById(appointmentSaveDTO.getAnimalID())){
                Doctor doctor = this.doctorRepository.findById(appointmentSaveDTO.getDoctorID()).orElseThrow();
                List<AvailableDate> doctorWorkDays = doctor.getAvailableDates();
                List<Appointment> doctorsAllAppointments = doctor.getAppointments();
                AvailableDate wantedDay = null;
                Appointment appointment = null;

                wantedDay = doctorWorkDays.stream().filter(availableDate -> availableDate.getAvailableDate().equals(appointmentSaveDTO.getAppointmentDate().toLocalDate())).findFirst().orElse(null);


                if (wantedDay == null){
                    throw new RuntimeException("The doctor is not working on this date!");
                }else{
                    appointment = doctorsAllAppointments.stream().filter(pseudoAppointment -> pseudoAppointment.getAppointmentDate().equals(appointmentSaveDTO.getAppointmentDate())).findFirst().orElse(null);

                    if (appointment != null){
                        throw new RuntimeException("There is another appointment on that hour");
                    }else{
                        try {
                            Appointment appointmentToBeCreated = new Appointment();
                            appointmentToBeCreated.setAppointmentDate(appointmentSaveDTO.getAppointmentDate());
                            appointmentToBeCreated.setDoctor(this.doctorRepository.findById(appointmentSaveDTO.getDoctorID()).orElseThrow());
                            appointmentToBeCreated.setAnimal(this.animalRepository.findById(appointmentSaveDTO.getAnimalID()).orElseThrow());

                            this.appointmentRepository.save(appointmentToBeCreated);

                            result = true;
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }else{
                throw new RuntimeException("There is no animal in database with this id!");
            }
        }else{
            throw new RuntimeException("There is no doctor in database with this id!");
        }
        return result;
    }

    @Override
    public Boolean update(AppointmentUpdateDTO appointmentUpdateDTO, Long id) {
        Boolean result = false;
        if (this.appointmentRepository.existsById(id)){
            Appointment previousAppointment = this.appointmentRepository.findById(id).orElseThrow();
            List<AvailableDate> doctorsWorkDays = previousAppointment.getDoctor().getAvailableDates();
            List<Appointment> doctorsAllAppointments = previousAppointment.getDoctor().getAppointments();
            AvailableDate wantedDay = null;
            Appointment appointment = null;

            wantedDay = doctorsWorkDays.stream().filter(availableDate -> availableDate.getAvailableDate().equals(appointmentUpdateDTO.getAppointmentDate().toLocalDate())).findFirst().orElse(null);

            if (wantedDay == null){
                throw new RuntimeException("The doctor is not working on this date!");
            }else{
                appointment = doctorsAllAppointments.stream().filter(pseudoAppointment -> pseudoAppointment.getAppointmentDate().equals(appointmentUpdateDTO.getAppointmentDate())).findFirst().orElse(null);

                if (appointment != null){
                    throw new RuntimeException("There is another appointment on that hour");
                }else{
                    Appointment newAppointment = new Appointment();
                    newAppointment.setId(id);
                    newAppointment.setDoctor(previousAppointment.getDoctor());
                    newAppointment.setAnimal(previousAppointment.getAnimal());
                    newAppointment.setAppointmentDate(appointmentUpdateDTO.getAppointmentDate());

                    try {
                        this.appointmentRepository.save(newAppointment);
                        result = true;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        return null;
                    }
                }
            }
            return result;
        }


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
        if (this.doctorRepository.existsById(doctorID)){
            // Önce istenen doktorun bütün appointmentlarını çağır
            // sonra o appointmentları tek tek gez ve istenen iki tarih arasındaki bütün randevularını başka bir listeye al ve onu dön
            // çalışan kişi bu randevulara bakacak ve müşteriye boş günleri söyelyip yani bu randevular dışındaki zamanları söyleyip randevu oluşturacak

            List<Appointment> allAppointmentsOfDoctor = this.doctorRepository.findById(doctorID).orElseThrow().getAppointments();

            List<Appointment> appointmentsBetweenGivenDates = allAppointmentsOfDoctor.stream().filter(appointment -> appointment.getAppointmentDate().isAfter(firstDate.atStartOfDay()) && appointment.getAppointmentDate().isBefore(secondDate.atStartOfDay())).toList();

            return appointmentsBetweenGivenDates;

        }else {
            throw new RuntimeException("There is no doctor in database with that id!");
        }
    }

    @Override
    public List<Appointment> findAppointmentByAnimalIdAndDate(Long animalID, LocalDate firstDate, LocalDate secondDate) {
        if (this.animalRepository.existsById(animalID)){
            // Amaç : verilen tarihler arasındaki belirli bir hayvanın randevularını görmek
            List<Appointment> allAppointmentsOfAnimal = this.animalRepository.findById(animalID).orElseThrow().getAppointmentList();

            List<Appointment> appointmentsBetweenGivenDates = allAppointmentsOfAnimal.stream().filter(appointment -> appointment.getAppointmentDate().isAfter(firstDate.atStartOfDay()) && appointment.getAppointmentDate().isBefore(secondDate.atStartOfDay())).toList();

            return appointmentsBetweenGivenDates;
        }else{
            throw new RuntimeException("There is no animal in database with that id!");
        }
    }
}
