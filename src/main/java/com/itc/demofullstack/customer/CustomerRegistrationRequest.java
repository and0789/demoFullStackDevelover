package com.itc.demofullstack.customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {

}
