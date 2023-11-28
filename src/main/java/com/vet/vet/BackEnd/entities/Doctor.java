package com.vet.vet.BackEnd.entities;

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

    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<AvailableDate> availableDates;
}
