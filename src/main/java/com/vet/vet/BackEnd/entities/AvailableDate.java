package com.vet.vet.BackEnd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "availableDates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "available_date")
    private LocalDate availableDate;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
