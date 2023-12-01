package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IVaccineService;
import com.vet.vet.BackEnd.dao.AnimalRepository;
import com.vet.vet.BackEnd.dao.VaccineRepository;
import com.vet.vet.BackEnd.dto.requestDto.VaccineSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.VaccineUpdateDTO;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Vaccine;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    public VaccineManager(VaccineRepository vaccineRepository, AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Vaccine> findAllByAnimalId(Long id) {

        if (this.animalRepository.existsById(id)){
            Animal animal = animalRepository.findById(id).orElseThrow();
            return animal.getVaccines();
        }

        return null;
    }

    @Override
    public List<Animal> findAllAnimalsThatNeedVaccine(String vaccineCode,LocalDate startDate, LocalDate endDate) {
        //First, I will fetch all the animals from the database. Then, I will go through each animal and their vaccination list
        // one by one and find the vaccine with the code I want and check if the expiration date of that vaccine is in the range I want.
        List<Animal> filteredAnimalList = new ArrayList<>();

        List<Animal> animalList = this.animalRepository.findAll();

        // This for loop iterates the animalList
        if (!animalList.isEmpty()){
            for (int i = 0; i < animalList.size(); i++){

                Animal animal = animalList.get(i);
                Vaccine latestSameCodeVaccine = null;

                // This for loop iterates the VaccineList that animal have
                // I start from the end and if I find the vaccine that has the code that I give in the parameters then
                // I capture that vaccine
                if (!animal.getVaccines().isEmpty()){
                    for (int j = animal.getVaccines().size() - 1; j>= 0;j--){
                        if (animal.getVaccines().get(j).getCode().equals(vaccineCode)){
                            latestSameCodeVaccine = animal.getVaccines().get(j);
                            break;
                        }
                    }
                }


                /*
                    If the captured vaccine is null, it means that this animal has not had a vaccine with this code before,
                    so I add it to the list (maybe it is meaningless, after all, maybe that animal should not have a vaccine with this code,
                    but I leave this decision to the veterinarian, just in case). If it is not null, I check whether the protection expiration date is
                    among the values I want and if so, I add it to the list.
                 */

                if (latestSameCodeVaccine == null){
                    filteredAnimalList.add(animal);
                }else{
                    if ((latestSameCodeVaccine.getProtectionEndDate().isAfter(startDate) || latestSameCodeVaccine.getProtectionEndDate().equals(startDate)) && latestSameCodeVaccine.getProtectionEndDate().isBefore(endDate)){
                        filteredAnimalList.add(animal);
                    }
                }
            }
        }

        return filteredAnimalList;
    }

    @Override
    public Vaccine findById(Long id) {
        if (this.vaccineRepository.existsById(id)){
            return this.vaccineRepository.findById(id).orElseThrow();
        }
        return null;
    }

    @Override
    public Boolean save(VaccineSaveDTO vaccineSaveDTO) {
        Boolean result = false;
        if (this.animalRepository.existsById(vaccineSaveDTO.getAnimalID())){
            Animal animal = this.animalRepository.findById(vaccineSaveDTO.getAnimalID()).orElseThrow();


            List<Vaccine> vaccineList = animal.getVaccines();
            int listSize = vaccineList.size();
            Vaccine latestSameCodeVaccine = null;
            if (listSize != 0){
                for (int i = listSize - 1; i>=0; i--){
                    if (Objects.equals(vaccineList.get(i).getCode(), vaccineSaveDTO.getCode())){
                        latestSameCodeVaccine = vaccineList.get(i);
                        break;
                    }
                }
            }

            if (latestSameCodeVaccine != null){

                boolean comparisonResult = vaccineSaveDTO.getProtectionStartDate().isAfter(latestSameCodeVaccine.getProtectionEndDate());

                /*
                    This if block compares the protection end date of the newest vaccine with this code among the animal's vaccines
                    with the protection start date of the vaccine sent to this method. If the protection has expired or if they're trying
                    to make vaccine on the same day the protection ends, it allows it, otherwise it does not allow it.
                    I leave the if blocks and else blocks empty so that the program can continue, since the same operation will be performed
                    in all of them, I will ensure that it gives an error in undesirable situations and finally I will perform the same operation for all of them.
                 */

                if (comparisonResult){

                } else if (vaccineSaveDTO.getProtectionStartDate().isEqual(latestSameCodeVaccine.getProtectionEndDate())) {

                }else{
                    throw new RuntimeException("You can't make vaccine to that animal because the latest vaccine's protection end day (that has the same code with this vaccine) has not come yet!");
                }

            }else {
                // There is no vaccine with that code before
            }

           // Turning my save dto to vaccine class
            Vaccine vaccine = new Vaccine();
            vaccine.setCode(vaccineSaveDTO.getCode());
            vaccine.setProtectionStartDate(vaccineSaveDTO.getProtectionStartDate());
            vaccine.setProtectionEndDate(vaccineSaveDTO.getProtectionEndDate());
            vaccine.setAnimal(animal);

            try {
                this.vaccineRepository.save(vaccine);
                result = true;

            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }

        }else{
            throw new RuntimeException("There is no animal with that ID!");
        }
        return result;
    }

    @Override
    public Boolean update(VaccineUpdateDTO vaccineUpdateDTO, Long id) {
        // If the employee tries to change the animalId of the vaccine then we need to check if the new animal can have that vaccine same as save method.
        Boolean result = false;
        vaccineUpdateDTO.setId(id);

        if(this.vaccineRepository.existsById(vaccineUpdateDTO.getId())){
            Vaccine vaccine = this.vaccineRepository.findById(vaccineUpdateDTO.getId()).orElseThrow();
            vaccine.setCode(vaccineUpdateDTO.getCode());
            try {
                this.vaccineRepository.save(vaccine);
                result = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }

        return result;
    }

    @Override
    public Boolean delete(Long id) {
        Boolean result = false;
        if (this.vaccineRepository.existsById(id)){
            try {
                this.vaccineRepository.deleteById(id);
                result = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

}
