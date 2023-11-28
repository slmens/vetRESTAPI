package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IDoctorService;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorManager(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

}
