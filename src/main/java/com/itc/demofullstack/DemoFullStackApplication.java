package com.itc.demofullstack;

import com.itc.demofullstack.customer.Customer;
import com.itc.demofullstack.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoFullStackApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoFullStackApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer andre = new Customer(
                    "Andre Septian",
                    "andre@gmail.com",
                    21
            );

            Customer fatimah = new Customer(

                    "Fatimah Azzahra",
                    "fatimah@gmail.com",
                    20
            );

            List<Customer> customers = List.of(andre, fatimah);
            customerRepository.saveAll(customers);
        };
    }
}
