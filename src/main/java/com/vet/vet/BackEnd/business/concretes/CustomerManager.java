package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.ICustomerService;
import com.vet.vet.BackEnd.dao.CustomerRepository;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final AnimalManager animalManager;

    public CustomerManager(CustomerRepository customerRepository,AnimalManager animalManager) {
        this.customerRepository = customerRepository;
        this.animalManager = animalManager;
    }


    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        try{
            return this.customerRepository.findById(id).orElseThrow();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean save(Customer customer) {
        Boolean result = false;

        try {
            this.customerRepository.save(customer);
            result = true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public Boolean update(Customer customer, Long id) {
        Boolean result = false;


        if (this.customerRepository.existsById(id)){
            customer.setId(id);

            try {
                this.customerRepository.save(customer);

                result = true;

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return result;
    }

    @Override
    public Boolean delete(Long id) {
        // If the result variable returns true, this means that no error occurred during the deletion process.
        Boolean result = false;

        if (this.customerRepository.existsById(id)){
            try {
                this.customerRepository.deleteById(id);
                result = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            throw  new RuntimeException(("there is no record with this id in database : " + id));
        }

        return result;
    }

    @Override
    public List<Animal> findAllAnimalsByCustomerId(Long id) {
        return this.animalManager.findAllAnimalsByCustomerId(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> allCustomers = this.customerRepository.findAll();

        List<Customer> filteredCustomers = allCustomers.stream().filter(customer -> name.equals(customer.getName())).collect(Collectors.toList());

        return filteredCustomers;
    }
}
