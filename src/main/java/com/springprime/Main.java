package com.springprime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Connecting to docker image
@SpringBootApplication
// We can search for the configuration if not specified by using @ComponentScan
// And then add the @EnableAutoConfiguration
@RestController
@RequestMapping("/api/v1/customers")
public class Main {
    private final CustomerRepository customerRepository;
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest (
        String name,
        String email,
        Integer age
    ){}
    @PostMapping("/add")
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setAge(request.age());
        customer.setName(request.name());
        customer.setEmail(request.email());
        customerRepository.save(customer);
    }
    @DeleteMapping("/delete/{customerID}")
    public void deleteUser (@PathVariable("customerID") Integer id){
        customerRepository.deleteById(id);
    }
}