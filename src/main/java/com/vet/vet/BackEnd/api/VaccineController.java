package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.VaccineManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {
    private final VaccineManager vaccineManager;

    public VaccineController(VaccineManager vaccineManager) {
        this.vaccineManager = vaccineManager;
    }

}
