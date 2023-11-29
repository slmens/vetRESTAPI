package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Boolean save(Customer customer);
    Boolean update(Customer customer,Long id);
    Boolean delete(Long id);

    List<Animal> findAllAnimalsByCustomerId(Long id);
    List<Customer> findByName(String name);
}
