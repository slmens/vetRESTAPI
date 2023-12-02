package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.CustomerManager;
import com.vet.vet.BackEnd.entities.Animal;
import com.vet.vet.BackEnd.entities.Customer;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerManager customerManager;

    public CustomerController(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @GetMapping("")
    public ResultData<List<Customer>> findAll(){
        return this.customerManager.findAll();
    }

    @GetMapping("/{id}")
    public ResultData<Customer> findById(@PathVariable("id") Long id){
        return this.customerManager.findById(id);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Customer customer){
        return this.customerManager.save(customer);
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Customer customer, @PathVariable("id") Long id){
        return this.customerManager.update(customer,id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id){
        return this.customerManager.delete(id);
    }

    @GetMapping("/animals/{id}")
    public ResultData<List<Animal>>indAllAnimalByCustomerId(@PathVariable("id") Long id){
        return this.customerManager.findAllAnimalsByCustomerId(id);
    }

    @GetMapping("/customer/{name}")
    public ResultData<List<Customer>> findByName(@PathVariable("name") String name){
        return this.customerManager.findByName(name);
    }

}
