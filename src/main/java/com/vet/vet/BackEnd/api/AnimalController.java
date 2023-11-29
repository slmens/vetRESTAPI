package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.AnimalManager;
import com.vet.vet.BackEnd.entities.Animal;
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
    public List<Animal> findAll(){
        return this.animalManager.findAll();
    }

    @GetMapping("/{id}")
    public Animal findById(@PathVariable("id") Long id){
        return this.animalManager.findById(id);
    }

    @PostMapping("/save")
    public Boolean save(@RequestBody Animal animal){
        return this.animalManager.save(animal);
    }

    @PutMapping("/{id}")
    public Boolean update(@RequestBody Animal animal, @PathVariable("id") Long id){
        return this.animalManager.update(animal,id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        return this.animalManager.delete(id);
    }

    @GetMapping("/{name}")
    public List<Animal> findByName(@PathVariable("name") String name){
        return this.animalManager.findByName(name);
    }

}
