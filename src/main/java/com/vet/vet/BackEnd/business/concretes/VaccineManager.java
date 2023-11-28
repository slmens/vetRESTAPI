package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IVaccineService;
import com.vet.vet.BackEnd.dao.VaccineRepository;
import org.springframework.stereotype.Service;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepository vaccineRepository;

    public VaccineManager(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }


}
