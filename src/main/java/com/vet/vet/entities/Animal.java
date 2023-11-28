package com.vet.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "animal_id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "species")
    private String species;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private String gender;

    @NotBlank
    @Column(name = "colour")
    private String colour;

    @Column (name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "animal")
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "animal")
    private List<Vaccine> vaccines;
}
