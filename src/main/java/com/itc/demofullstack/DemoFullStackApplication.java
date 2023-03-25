package com.itc.demofullstack;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.itc.demofullstack.customer.Customer;
import com.itc.demofullstack.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class DemoFullStackApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoFullStackApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            var faker = new Faker();
            Random random = new Random();
            Name name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();
            Customer customer = new Customer(
                    firstName + " " + lastName,
                    name.firstName().toLowerCase() + "." + name.lastName().toLowerCase() + "@itc.ac.id",
                    random.nextInt(16, 99)
            );

            customerRepository.save(customer);
        };
    }
}
