package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.entities.Animal;

import java.util.List;

public interface IAnimalService {
    List<Animal> findAll();
    Animal findById(Long id);
    Boolean save(Animal animal);
    Boolean update(Animal animal,Long id);
    Boolean delete(Long id);

    List<Animal> findAllAnimalsByCustomerId(Long id);
    List<Animal> findByName(String name);
}
