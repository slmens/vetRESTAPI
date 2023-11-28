package com.vet.vet.BackEnd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "vaccine_code",length = 100,unique = true)
    private String code;

    @NotBlank
    @Column(name = "protection_start_date")
    private LocalDate protectionStartDate;

    @NotBlank
    @Column(name = "protection_end_date")
    private LocalDate protectionEndDate;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
