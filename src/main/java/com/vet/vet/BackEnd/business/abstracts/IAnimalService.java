package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.AnimalSaveDTO;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;

import java.util.List;

public interface IAnimalService {
    ResultData<List<Animal>> findAll();
    ResultData<Animal> findById(Long id);
    Result save(AnimalSaveDTO animalSaveDTO);
    Result update(AnimalSaveDTO animalSaveDTO,Long id);
    Result delete(Long id);

    List<Animal> findAllAnimalsByCustomerId(Long id);
    ResultData<List<Animal>> findByName(String name);
}
