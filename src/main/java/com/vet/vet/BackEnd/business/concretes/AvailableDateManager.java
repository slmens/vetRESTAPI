package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAvailableDateService;
import com.vet.vet.BackEnd.dao.AvailableDateRepository;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

}
