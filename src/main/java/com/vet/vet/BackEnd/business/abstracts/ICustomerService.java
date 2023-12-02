package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Customer;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;

import java.util.List;

public interface ICustomerService {
    ResultData<List<Customer>> findAll();
    ResultData<Customer> findById(Long id);
    Result save(Customer customer);
    Result update(Customer customer,Long id);
    Result delete(Long id);

    ResultData<List<Animal>> findAllAnimalsByCustomerId(Long id);
    ResultData<List<Customer>> findByName(String name);
}
