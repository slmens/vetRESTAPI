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
import com.vet.vet.core.exception.NotFoundException;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
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
    public ResultData<Appointment> findById(Long id) {
        ResultData<Appointment> resultData = new ResultData<>(false,"Appointment couldn't found!","404",null);
        if (this.appointmentRepository.existsById(id)){
            Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Appointment couldn't found!"));
            resultData.setStatus(true);
            resultData.setMessage("Appointment found!");
            resultData.setHttpCode("200");
            resultData.setData(appointment);
        }
        return resultData;
    }

    @Override
    public ResultData<List<Appointment>> findAll() {
        ResultData<List<Appointment>> resultData = new ResultData<>(true,"Appointment list found!","200",this.appointmentRepository.findAll());
        return resultData;
    }

    @Override
    public Result save(AppointmentSaveDTO appointmentSaveDTO) {
        Result result = new Result(false,"Appointment couldn't saved!","400");

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

                            result.setHttpCode("201");
                            result.setStatus(true);
                            result.setMessage("Appointment saved!");
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
    public Result update(AppointmentUpdateDTO appointmentUpdateDTO, Long id) {
        Result result = new Result(false,"Appointment couldn't updated!","400");

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
                        result.setMessage("Appointment updated!");
                        result.setStatus(true);
                        result.setHttpCode("201");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        return null;
                    }
                }
            }
        }


        return result;
    }

    @Override
    public Result delete(Long id) {
        Result result = new Result(false,"Appointment couldn't deleted!","400");
        if (this.appointmentRepository.existsById(id)){
            this.appointmentRepository.deleteById(id);
            result.setMessage("Appointment deleted!");
            result.setStatus(true);
            result.setHttpCode("204");
        }

        return result;
    }

    @Override
    public ResultData<List<Appointment>> findAppointmentByDoctorIdAndDate(Long doctorID, LocalDate firstDate, LocalDate secondDate) {
        ResultData<List<Appointment>> resultData = new ResultData<>(false,"Appointment list by doctor id couldn't found!","404",null);
        if (this.doctorRepository.existsById(doctorID)){
            // Önce istenen doktorun bütün appointmentlarını çağır
            // sonra o appointmentları tek tek gez ve istenen iki tarih arasındaki bütün randevularını başka bir listeye al ve onu dön
            // çalışan kişi bu randevulara bakacak ve müşteriye boş günleri söyelyip yani bu randevular dışındaki zamanları söyleyip randevu oluşturacak

            List<Appointment> allAppointmentsOfDoctor = this.doctorRepository.findById(doctorID).orElseThrow().getAppointments();

            List<Appointment> appointmentsBetweenGivenDates = allAppointmentsOfDoctor.stream().filter(appointment -> appointment.getAppointmentDate().isAfter(firstDate.atStartOfDay()) && appointment.getAppointmentDate().isBefore(secondDate.atStartOfDay())).toList();

            resultData.setStatus(true);
            resultData.setMessage("Appointment list by doctor id found!");
            resultData.setHttpCode("200");
            resultData.setData(appointmentsBetweenGivenDates);

        }else {
            throw new RuntimeException("There is no doctor in database with that id!");
        }
        return resultData;
    }

    @Override
    public ResultData<List<Appointment>> findAppointmentByAnimalIdAndDate(Long animalID, LocalDate firstDate, LocalDate secondDate) {
        ResultData<List<Appointment>> resultData = new ResultData<>(false,"Appointment list by animal id couldn't found!","404",null);
        if (this.animalRepository.existsById(animalID)){
            // Amaç : verilen tarihler arasındaki belirli bir hayvanın randevularını görmek
            List<Appointment> allAppointmentsOfAnimal = this.animalRepository.findById(animalID).orElseThrow().getAppointmentList();

            List<Appointment> appointmentsBetweenGivenDates = allAppointmentsOfAnimal.stream().filter(appointment -> appointment.getAppointmentDate().isAfter(firstDate.atStartOfDay()) && appointment.getAppointmentDate().isBefore(secondDate.atStartOfDay())).toList();

            resultData.setStatus(true);
            resultData.setMessage("Appointment list by animal id found!");
            resultData.setHttpCode("200");
            resultData.setData(appointmentsBetweenGivenDates);
        }else{
            throw new RuntimeException("There is no animal in database with that id!");
        }

        return resultData;
    }
}
