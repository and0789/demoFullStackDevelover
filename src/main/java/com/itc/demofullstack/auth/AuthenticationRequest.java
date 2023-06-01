package com.itc.demofullstack.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}