package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.DoctorManager;
import com.vet.vet.BackEnd.dto.requestDto.doctor.DoctorSaveDTO;
import com.vet.vet.BackEnd.dto.responseDto.doctor.DoctorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorManager doctorManager;

    public DoctorController(DoctorManager doctorManager) {
        this.doctorManager = doctorManager;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DoctorResponseDTO findById(@PathVariable("id") Long id){
        return this.doctorManager.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponseDTO> findAll(){
        List<DoctorResponseDTO> doctorResponseDTOS = this.doctorManager.findAll();
        return doctorResponseDTOS;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean save(@RequestBody DoctorSaveDTO doctorSaveDTO){
        return this.doctorManager.save(doctorSaveDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean update(@RequestBody DoctorSaveDTO doctorSaveDTO, @PathVariable("id") Long id){
        return this.doctorManager.update(doctorSaveDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean delete(@PathVariable("id") Long id){
        return this.doctorManager.delete(id);
    }
}
