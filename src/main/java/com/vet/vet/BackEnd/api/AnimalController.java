package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.AnimalManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalManager animalManager;

    public AnimalController(AnimalManager animalManager) {
        this.animalManager = animalManager;
    }


}
