package com.vet.vet.BackEnd.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineSaveDTO {

    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private LocalDate protectionStartDate;

    @NotBlank
    private LocalDate protectionEndDate;

    @NotBlank
    private Long animalID;
}
