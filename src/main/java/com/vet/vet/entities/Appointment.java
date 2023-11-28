package com.vet.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "appointment_id")
    private Long id;

    @NotBlank
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private Doctor doctor;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id",referencedColumnName = "animal_id")
    private Animal animal;
}
