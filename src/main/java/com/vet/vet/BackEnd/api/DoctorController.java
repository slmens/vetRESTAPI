package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.DoctorManager;
import com.vet.vet.BackEnd.dto.requestDto.DoctorSaveDTO;
import com.vet.vet.BackEnd.dto.responseDto.DoctorResponseDTO;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
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
    public ResultData<DoctorResponseDTO> findById(@PathVariable("id") Long id){
        return this.doctorManager.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<DoctorResponseDTO>> findAll(){
        return this.doctorManager.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Result save(@RequestBody DoctorSaveDTO doctorSaveDTO){
        return this.doctorManager.save(doctorSaveDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result update(@RequestBody DoctorSaveDTO doctorSaveDTO, @PathVariable("id") Long id){
        return this.doctorManager.update(doctorSaveDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        return this.doctorManager.delete(id);
    }
}
