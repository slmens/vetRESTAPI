package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.VaccineSaveDTO;
import com.vet.vet.BackEnd.dto.requestDto.VaccineUpdateDTO;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Vaccine;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    ResultData<List<Vaccine>> findAllByAnimalId(Long id);
    ResultData<List<Animal>> findAllAnimalsThatNeedVaccine(String vaccineCode,LocalDate startDate, LocalDate endDate);
    ResultData<Vaccine> findById(Long id);
    Result save(VaccineSaveDTO vaccineSaveDTO);
    Result update(VaccineUpdateDTO vaccineUpdateDTO, Long id);
    Result delete(Long id);
}