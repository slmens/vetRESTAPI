package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.VaccineSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.VaccineUpdateDTO;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    List<Vaccine> findAllByAnimalId(Long id);
    List<Animal> findAllAnimalsThatNeedVaccine(String vaccineCode,LocalDate startDate, LocalDate endDate);
    Vaccine findById(Long id);
    Boolean save(VaccineSaveDTO vaccineSaveDTO);
    Boolean update(VaccineUpdateDTO vaccineUpdateDTO, Long id);
    Boolean delete(Long id);
}