package com.vet.vet.BackEnd.dto.responseDto;

import com.vet.vet.BackEnd.entities.Doctor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AppointmentFrontendDTO {

    private Long id;

    private LocalDateTime appointmentDate;

    private Long animalID;

    public Doctor doctor;

    public AppointmentFrontendDTO(Long id, LocalDateTime appointmentDate, Long animalID, Doctor doctor) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.animalID = animalID;
        this.doctor = doctor;
    }
}
