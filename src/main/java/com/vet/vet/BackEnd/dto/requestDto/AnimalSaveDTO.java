package com.vet.vet.BackEnd.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSaveDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    private String breed;

    private String gender;

    @NotBlank
    private String colour;

    private LocalDate dateOfBirth;

    @NotNull
    private Long customerID;
}
