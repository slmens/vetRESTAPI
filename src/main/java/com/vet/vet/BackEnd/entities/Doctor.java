package com.vet.vet.BackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Person {

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"doctor"})
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = {"doctor"})
    private List<AvailableDate> availableDates;


}
