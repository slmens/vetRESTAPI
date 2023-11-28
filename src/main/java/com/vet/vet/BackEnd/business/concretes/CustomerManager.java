package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.ICustomerService;
import com.vet.vet.BackEnd.dao.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

}
