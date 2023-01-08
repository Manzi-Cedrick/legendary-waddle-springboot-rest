package com.springprime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    @DeleteMapping("{customerID}")
    public void deleteUser(@PathVariable("customerID") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerID}")
    public void updateUser(@PathVariable("customerID") Integer id,@RequestBody NewCustomerRequest request){
        Customer customer = customerRepository.findById(id).get();
        if(Objects.nonNull(customer.getName()) && !"".equalsIgnoreCase(customer.getName())){
            customer.setName(request.name());
        }
        if(Objects.nonNull(customer.getEmail()) && !"".equalsIgnoreCase(customer.getEmail())){
            customer.setEmail(request.email());
        }
        if(Objects.nonNull(customer.getAge())){
            customer.setAge(request.age());
        }
        customerRepository.save(customer);
    }
}