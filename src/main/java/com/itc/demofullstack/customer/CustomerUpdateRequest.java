package com.itc.demofullstack.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
