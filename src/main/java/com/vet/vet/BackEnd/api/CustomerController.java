package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.CustomerManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerManager customerManager;

    public CustomerController(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

}
