package com.vet.vet.BackEnd.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSaveDTO {

    private Long id;

    @NotNull
    private LocalDateTime appointmentDate;

    @NotNull
    private Long doctorID;

    @NotNull
    private Long animalID;
}
