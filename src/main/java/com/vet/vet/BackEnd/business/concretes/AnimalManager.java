package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAnimalService;
import com.vet.vet.BackEnd.dao.AnimalRepository;
import com.vet.vet.BackEnd.dao.CustomerRepository;
import com.vet.vet.BackEnd.dto.requestDto.doctor.AnimalSaveDTO;
import com.vet.vet.BackEnd.entities.Animal;
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

    @Override
    public List<Animal> findAll() {
        return this.animalRepository.findAll();
    }

    @Override
    public Animal findById(Long id) {
        try{
            return this.animalRepository.findById(id).orElseThrow();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean save(AnimalSaveDTO animalSaveDTO) {
        Boolean result = false;

        try {
            Animal animal = modelMapper.map(animalSaveDTO,Animal.class);
            animal.setCustomer(this.customerRepository.findById(animalSaveDTO.getCustomerID()).orElseThrow());
            this.animalRepository.save(animal);
            result = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public Boolean update(AnimalSaveDTO animalSaveDTO, Long id) {
        Boolean result = false;


        if (this.animalRepository.existsById(id)){
            animalSaveDTO.setId(id);

            try {
                Animal animal = modelMapper.map(animalSaveDTO,Animal.class);
                animal.setCustomer(this.customerRepository.findById(animalSaveDTO.getCustomerID()).orElseThrow());
                this.animalRepository.save(animal);

                result = true;

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return result;
    }

    @Override
    public Boolean delete(Long id) {
        // If the result variable returns true, this means that no error occurred during the deletion process.
        Boolean result = false;

        try {
            this.animalRepository.deleteById(id);
            result = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    // This method uses by CustomerManager
    @Override
    public List<Animal> findAllAnimalsByCustomerId(Long id) {
        return this.animalRepository.findAllByCustomerId(id);
    }

    @Override
    public List<Animal> findByName(String name) {
        List<Animal> allAnimals = this.animalRepository.findAll();

        List<Animal> filteredAnimals = allAnimals.stream().filter(animal -> name.equals(animal.getName())).collect(Collectors.toList());

        return filteredAnimals;
    }
}
