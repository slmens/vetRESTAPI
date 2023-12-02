package com.vet.vet.BackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "available_date")
    private LocalDate availableDate;

    @NotNull
    @JsonIgnoreProperties(value = {"appointments","availableDates","address","city","mail","phone"})
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
