package com.amigoscode.customer.domain.repository;

import com.amigoscode.customer.domain.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
