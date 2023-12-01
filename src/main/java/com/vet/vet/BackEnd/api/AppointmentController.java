package com.vet.vet.BackEnd.api;

import com.vaadin.open.App;
import com.vet.vet.BackEnd.business.concretes.AppointmentManager;
import com.vet.vet.BackEnd.dto.requestDto.AppointmentSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.AvailableDateSaveDTO;
import com.vet.vet.BackEnd.entities.Appointment;
import com.vet.vet.BackEnd.entities.AvailableDate;
import org.atmosphere.config.service.Get;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentManager appointmentManager;

    public AppointmentController(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Appointment findById(@PathVariable("id") Long id){
        return this.appointmentManager.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findAll(){
        return this.appointmentManager.findAll();
    }

    @GetMapping("/doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findByDoctorId(@RequestParam("doctorID") Long id, @RequestParam("firstDate")LocalDate firstDate, @RequestParam("secondDate")LocalDate secondDate){
        return this.appointmentManager.findAppointmentByDoctorIdAndDate(id,firstDate,secondDate);
    }

    @GetMapping("/animal")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findByAnimalId(@RequestParam("animalID") Long id,@RequestParam("firstDate") LocalDate firstDate, @RequestParam("secondDate") LocalDate secondDate){
        return this.appointmentManager.findAppointmentByAnimalIdAndDate(id,firstDate,secondDate);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean save(@RequestBody AppointmentSaveDTO appointmentSaveDTO){
        return this.appointmentManager.save(appointmentSaveDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean update(@RequestBody AppointmentSaveDTO appointmentSaveDTO, @PathVariable("id") Long id){
        return this.appointmentManager.update(appointmentSaveDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean delete(@PathVariable("id") Long id){
        return this.appointmentManager.delete(id);
    }
}
