package com.amigoscode.customer.presentation.controller;

import com.amigoscode.customer.presentation.request.CustomerRegistrationRequest;
import com.amigoscode.customer.domain.usecase.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Mono<Void> registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        return customerService.registerCustomer(customerRegistrationRequest)
                .doOnSuccess(aVoid -> log.info("Customer registration successful"))
                .doOnError(error -> log.error("Customer registration failed", error));
    }
}
