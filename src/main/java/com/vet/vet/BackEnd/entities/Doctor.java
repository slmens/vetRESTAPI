package com.vet.vet.BackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Person {

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"doctor"})
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"doctor"})
    private List<AvailableDate> availableDates;
}
