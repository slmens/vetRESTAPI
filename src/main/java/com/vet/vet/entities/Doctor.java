package com.vet.vet.entities;

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

    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER)
    private List<AvailableDate> availableDates;
}
