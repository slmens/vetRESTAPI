package com.vet.vet.BackEnd.dto.requestDto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSaveDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    private String breed;

    private String gender;

    @NotBlank
    private String colour;

    private LocalDate dateOfBirth;

    @NotBlank
    private Long customerID;
}
