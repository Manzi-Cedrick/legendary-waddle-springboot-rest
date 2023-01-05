package com.springprime;

import org.springframework.data.jpa.repository.JpaRepository;

// We pass the entity datatype and the datatype of customer id
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
