package com.vet.vet.BackEnd.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineUpdateDTO {

    private Long id;

    @NotBlank
    private String code;
}
