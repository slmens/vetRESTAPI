package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.AppointmentManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentManager appointmentManager;

    public AppointmentController(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }
}
