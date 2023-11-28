package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAppointmentService;
import com.vet.vet.BackEnd.dao.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentManager(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


}
