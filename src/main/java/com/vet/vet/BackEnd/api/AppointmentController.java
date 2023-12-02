package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.AppointmentManager;
import com.vet.vet.BackEnd.dto.requestDto.AppointmentSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.AppointmentUpdateDTO;
import com.vet.vet.BackEnd.entities.Appointment;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
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
    public ResultData<Appointment> findById(@PathVariable("id") Long id){
        return this.appointmentManager.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Appointment>> findAll(){
        return this.appointmentManager.findAll();
    }

    @GetMapping("/doctor")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Appointment>> findByDoctorId(
            @RequestParam("doctorID") Long id,
            @RequestParam("firstDate")LocalDate firstDate,
            @RequestParam("secondDate")LocalDate secondDate){
        return this.appointmentManager.findAppointmentByDoctorIdAndDate(id,firstDate,secondDate);
    }

    @GetMapping("/animal")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Appointment>> findByAnimalId(
            @RequestParam("animalID") Long id,
            @RequestParam("firstDate") LocalDate firstDate,
            @RequestParam("secondDate") LocalDate secondDate){
        return this.appointmentManager.findAppointmentByAnimalIdAndDate(id,firstDate,secondDate);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Result save(@RequestBody AppointmentSaveDTO appointmentSaveDTO){
        return this.appointmentManager.save(appointmentSaveDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result update(@RequestBody AppointmentUpdateDTO appointmentUpdateDTO, @PathVariable("id") Long id){
        return this.appointmentManager.update(appointmentUpdateDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        return this.appointmentManager.delete(id);
    }
}
