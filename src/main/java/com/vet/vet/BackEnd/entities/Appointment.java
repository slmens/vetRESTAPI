package com.vet.vet.BackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @JsonIgnoreProperties(value = {"appointments","availableDates"})
    @NotNull
    @ManyToOne()
    private Doctor doctor;

    @JsonIgnoreProperties(value = {"appointmentList","vaccines","animalList"})
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
