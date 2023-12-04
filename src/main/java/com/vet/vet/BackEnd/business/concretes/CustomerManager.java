package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.ICustomerService;
import com.vet.vet.BackEnd.dao.CustomerRepository;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Customer;
import com.vet.vet.core.exception.RecordNotFoundException;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
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

    // This method helps to find all customers
    @Override
    public ResultData<List<Customer>> findAll() {
        return new ResultData<>(true,"Customer list found!","200", this.customerRepository.findAll());
    }

    // This method helps to find specific customer
    @Override
    public ResultData<Customer> findById(Long id) {
        ResultData<Customer> resultData = new ResultData<>(false,"Customer not found!","404",null);
        try{
            Customer customer = this.customerRepository.findById(id).orElseThrow();
            resultData.setStatus(true);
            resultData.setData(customer);
            resultData.setMessage("Customer found!");
            resultData.setHttpCode("200");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resultData;
    }

    // This method helps to save a customer
    @Override
    public Result save(Customer customer) {
        Result result = new Result(false,"Customer couldn't saved!","400");

        try {
            this.customerRepository.save(customer);
            result.setStatus(true);
            result.setMessage("Customer saved!");
            result.setHttpCode("201");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    // This method helps to update a customer
    @Override
    public Result update(Customer customer, Long id) {
        Result result = new Result(false,"Customer couldn't updated!","400");


        if (this.customerRepository.existsById(id)){
            customer.setId(id);

            try {
                this.customerRepository.save(customer);

                result.setStatus(true);
                result.setMessage("Customer updated!");
                result.setHttpCode("201");

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    // This method helps to delete a customer
    @Override
    public Result delete(Long id) {
        // If the result variable returns true, this means that no error occurred during the deletion process.
        Result result = new Result(false,"Customer couldn't deleted!","400");

        if (this.customerRepository.existsById(id)){
            try {
                this.customerRepository.deleteById(id);
                result.setStatus(true);
                result.setMessage("Customer deleted!");
                result.setHttpCode("204");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            throw  new RecordNotFoundException(("there is no record with this id in database : " + id));
        }

        return result;
    }

    // This method helps to find all animals that has specific customer id
    @Override
    public ResultData<List<Animal>> findAllAnimalsByCustomerId(Long id) {
        ResultData<List<Animal>> resultData = new ResultData<>(false,"Animal list not found!","404",null);

        if (this.customerRepository.existsById(id)){
            resultData.setHttpCode("200");
            resultData.setMessage("Animal list found!");
            resultData.setStatus(true);
            resultData.setData(this.animalManager.findAllAnimalsByCustomerId(id));
        }
        return resultData ;
    }

    // This method helps to find all animals that has specific name
    @Override
    public ResultData<List<Customer>> findByName(String name) {
        List<Customer> allCustomers = this.customerRepository.findAll();

        List<Customer> filteredCustomers = allCustomers.stream().filter(customer -> name.equals(customer.getName())).collect(Collectors.toList());

        ResultData<List<Customer>> resultData = new ResultData<>(true,"Customer list found!","200",filteredCustomers);

        return resultData;
    }
}
