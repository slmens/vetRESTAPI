package com.vet.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "phone_number")
    private String phone;

    @NotBlank
    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @NotBlank
    @Column(name = "city")
    private String city;
}
