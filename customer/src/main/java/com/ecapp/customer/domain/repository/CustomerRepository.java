package com.ecapp.customer.domain.repository;

import com.ecapp.customer.domain.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
