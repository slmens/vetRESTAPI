package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.AnimalManager;
import com.vet.vet.BackEnd.dto.requestDto.AnimalSaveDTO;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalManager animalManager;

    public AnimalController(AnimalManager animalManager) {
        this.animalManager = animalManager;
    }


    @GetMapping("")
    public ResultData<List<Animal>> findAll(){
        return this.animalManager.findAll();
    }

    @GetMapping("/{id}")
    public ResultData<Animal> findById(@PathVariable("id") Long id){
        return this.animalManager.findById(id);
    }

    @PostMapping("/save")
    public Result save(@Valid @RequestBody AnimalSaveDTO animalSaveDTO){
        return this.animalManager.save(animalSaveDTO);
    }

    @PutMapping("/{id}")
    public Result update(@Valid @RequestBody AnimalSaveDTO animalSaveDTO, @PathVariable("id") Long id){
        return this.animalManager.update(animalSaveDTO,id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id){
        return this.animalManager.delete(id);
    }

    @GetMapping("/animal/{name}")
    public ResultData<List<Animal>> findByName(@PathVariable("name") String name){
        return this.animalManager.findByName(name);
    }

}
