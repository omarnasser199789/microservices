package com.amigoscode.customer.presentation.request;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
