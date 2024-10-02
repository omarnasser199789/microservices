package com.ecapp.customer.domain.usecase;

import com.ecapp.amqp.RabbitMQMessageProducer;
import com.ecapp.clients.fraud.FraudClient;
import com.ecapp.clients.notification.NotificationRequest;
import com.ecapp.customer.domain.entity.Customer;
import com.ecapp.customer.presentation.request.CustomerRegistrationRequest;
import com.ecapp.customer.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Mono<Void> registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // Save the customer and return the Mono
        return customerRepository.save(customer)
                .flatMap(savedCustomer -> {
                    // Call the fraud client reactively
                    return Mono.fromCallable(() -> fraudClient.isFraudster(savedCustomer.getId())) // Wrap in Mono
                            .flatMap(fraudCheckResponse -> {
                                if (fraudCheckResponse.isFraudster()) {
                                    return Mono.error(new IllegalStateException("fraudster"));
                                }

                                // Prepare the notification request
                                NotificationRequest notificationRequest = new NotificationRequest(
                                        savedCustomer.getId(),
                                        savedCustomer.getEmail(),
                                        String.format("Hi %s, welcome to Amigoscode...", savedCustomer.getFirstName())
                                );

                                // Publish the notification reactively
                                return Mono.fromRunnable(() -> rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key"));
                            });
                })
                .then(); // Use then() to complete the Mono<Void> operation
    }
}
