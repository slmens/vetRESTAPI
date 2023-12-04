package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAnimalService;
import com.vet.vet.BackEnd.dao.AnimalRepository;
import com.vet.vet.BackEnd.dao.CustomerRepository;
import com.vet.vet.BackEnd.dto.requestDto.AnimalSaveDTO;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.core.exception.NotFoundException;
import com.vet.vet.core.exception.RecordNotFoundException;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public AnimalManager(AnimalRepository animalRepository, ModelMapper modelMapper,CustomerRepository customerRepository) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    // This method helps to find all animals in the database
    @Override
    public ResultData<List<Animal>> findAll() {

        List<Animal> animalList = this.animalRepository.findAll();

        ResultData<List<Animal>> resultData = new ResultData<>(true,"Animal list!","200",animalList);
        return resultData;
    }

    // This method helps to find specific animal in the database by id
    @Override
    public ResultData<Animal> findById(Long id) {
        try{
            Animal animal = this.animalRepository.findById(id).orElseThrow(() -> new NotFoundException("Animal not found in database!"));
            ResultData<Animal> resultData = new ResultData<>(true,"Animal","200",animal);
            return resultData;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    // This method helps to save animal to the database
    @Override
    public Result save(AnimalSaveDTO animalSaveDTO) {
        Result result = new Result(false,"Animal couldn't saved!","404");
        try {
            Animal animal = modelMapper.map(animalSaveDTO,Animal.class);
            animal.setCustomer(this.customerRepository.findById(animalSaveDTO.getCustomerID()).orElseThrow());
            this.animalRepository.save(animal);

            result.setMessage("Animal saved successfully!");
            result.setHttpCode("201");
            result.setStatus(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    // This method helps to update specific animal in the database
    @Override
    public Result update(AnimalSaveDTO animalSaveDTO, Long id) {
        Result result = new Result(false,"Animal couldn't updated!","404");


        if (this.animalRepository.existsById(id)){

            try {
                Animal animal = modelMapper.map(animalSaveDTO,Animal.class);
                animal.setId(id);
                animal.setCustomer(this.customerRepository.findById(animalSaveDTO.getCustomerID()).orElseThrow(() -> new NotFoundException("The customer you wanted couldn't found!")));
                this.animalRepository.save(animal);

                result.setMessage("Animal updated successfully!");
                result.setHttpCode("200");
                result.setStatus(true);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return result;
    }

    // This method helps to delete spesific animal in the database
    @Override
    public Result delete(Long id) {
        // If the result variable returns true, this means that no error occurred during the deletion process.
        Result result = new Result(false,"Animal couldn't deleted","204");

        if (this.animalRepository.existsById(id)){
            try {
                this.animalRepository.deleteById(id);

                result.setMessage("Animal deleted successfully!");
                result.setHttpCode("200");
                result.setStatus(true);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            throw  new RecordNotFoundException(("there is no record with this id in database : " + id));
        }

        return result;
    }

    // This method uses by CustomerManager to find animals that has specific customer id
    @Override
    public List<Animal> findAllAnimalsByCustomerId(Long id) {
        return this.animalRepository.findAllByCustomerId(id);
    }

    @Override
    public ResultData<List<Animal>> findByName(String name) {

        List<Animal> allAnimals = this.animalRepository.findAll();

        List<Animal> filteredAnimals = allAnimals.stream().filter(animal -> name.equals(animal.getName())).collect(Collectors.toList());

        ResultData<List<Animal>> resultData = new ResultData<>(true,"Animal list by animal name","200",filteredAnimals);

        return resultData;
    }
}
