package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.doctor.AnimalSaveDTO;
import com.vet.vet.BackEnd.entities.Animal;

import java.util.List;

public interface IAnimalService {
    List<Animal> findAll();
    Animal findById(Long id);
    Boolean save(AnimalSaveDTO animalSaveDTO);
    Boolean update(AnimalSaveDTO animalSaveDTO,Long id);
    Boolean delete(Long id);

    List<Animal> findAllAnimalsByCustomerId(Long id);
    List<Animal> findByName(String name);
}
