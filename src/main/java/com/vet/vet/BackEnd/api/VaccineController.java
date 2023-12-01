package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.VaccineManager;
import com.vet.vet.BackEnd.dto.requestDto.VaccineSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.VaccineUpdateDTO;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Vaccine;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {
    private final VaccineManager vaccineManager;

    public VaccineController(VaccineManager vaccineManager) {
        this.vaccineManager = vaccineManager;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Vaccine findById(@PathVariable("id") Long id){
        return this.vaccineManager.findById(id);
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findAllByAnimalId(@PathVariable("id") Long id){
        return this.vaccineManager.findAllByAnimalId(id);
    }


    @GetMapping("/animal")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAllAnimalsThatNeedVaccine(@RequestParam("vaccineCode") String vaccineCode,@RequestParam("startDate")LocalDate startDate, @RequestParam("endDate") LocalDate endDate){
        return this.vaccineManager.findAllAnimalsThatNeedVaccine(vaccineCode,startDate,endDate);
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean save(@RequestBody VaccineSaveDTO vaccineSaveDTO){
        return this.vaccineManager.save(vaccineSaveDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean update(@RequestBody VaccineUpdateDTO vaccineUpdateDTO, @PathVariable("id") Long id){
        return this.vaccineManager.update(vaccineUpdateDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean delete(@PathVariable("id") Long id){
        return this.vaccineManager.delete(id);
    }
}
