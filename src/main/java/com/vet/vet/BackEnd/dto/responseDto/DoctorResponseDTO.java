package com.vet.vet.BackEnd.dto.responseDto;

import com.vet.vet.BackEnd.entities.Appointment;
import com.vet.vet.BackEnd.entities.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDTO {

    private Long id;

    private String name;

    private String mail;

    private String city;

    private List<Appointment> appointments;

    private List<AvailableDate> availableDates;
}
