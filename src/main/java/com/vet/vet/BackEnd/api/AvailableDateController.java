package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.AvailableDateManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/availableDates")
public class AvailableDateController {

    private final AvailableDateManager availableDateManager;

    public AvailableDateController(AvailableDateManager availableDateManager) {
        this.availableDateManager = availableDateManager;
    }
}
