package com.vet.vet.BackEnd.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateDTO {

    private Long id;

    @NotNull
    private LocalDateTime appointmentDate;
}
