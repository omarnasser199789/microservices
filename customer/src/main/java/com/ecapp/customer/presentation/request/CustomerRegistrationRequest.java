package com.ecapp.customer.presentation.request;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
