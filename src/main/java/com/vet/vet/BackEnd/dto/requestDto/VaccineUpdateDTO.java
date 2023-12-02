package com.vet.vet.BackEnd.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineUpdateDTO {

    @Positive
    private Long id;

    @NotBlank
    private String code;
}
