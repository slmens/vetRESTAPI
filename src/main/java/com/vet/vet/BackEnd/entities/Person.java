package com.vet.vet.BackEnd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 20,message = "Input should not exceed 20 characters!")
    @Column(name = "phone_number")
    private String phone;

    @NotBlank
    @Email
    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @NotBlank
    @Column(name = "city")
    private String city;
}
