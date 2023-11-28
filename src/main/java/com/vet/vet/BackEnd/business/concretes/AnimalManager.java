package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAnimalService;
import com.vet.vet.BackEnd.dao.AnimalRepository;
import org.springframework.stereotype.Service;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepository animalRepository;

    public AnimalManager(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
}
