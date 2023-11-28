package com.vet.vet.BackEnd.dto.requestDto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSaveDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String mail;

    @NotBlank
    private String address;

    @NotBlank
    private String city;
}
