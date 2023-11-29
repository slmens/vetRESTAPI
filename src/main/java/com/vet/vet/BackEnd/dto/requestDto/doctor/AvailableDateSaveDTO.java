package com.vet.vet.BackEnd.dto.requestDto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateSaveDTO {

    private Long id;

    @NotBlank
    private LocalDate availableDate;

    @NotBlank
    private Long doctorID;
}
