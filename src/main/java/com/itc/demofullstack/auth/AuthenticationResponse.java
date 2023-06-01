package com.itc.demofullstack.auth;

import com.itc.demofullstack.customer.CustomerDTO;


public record AuthenticationResponse (
        String token,
        CustomerDTO customerDTO){
}