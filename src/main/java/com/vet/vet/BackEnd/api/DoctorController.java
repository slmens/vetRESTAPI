package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.DoctorManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorManager doctorManager;

    public DoctorController(DoctorManager doctorManager) {
        this.doctorManager = doctorManager;
    }
}
