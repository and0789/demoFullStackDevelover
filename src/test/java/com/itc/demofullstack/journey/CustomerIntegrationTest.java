package com.itc.demofullstack.journey;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.itc.demofullstack.customer.CustomerDTO;
import com.itc.demofullstack.customer.CustomerRegistrationRequest;
import com.itc.demofullstack.customer.CustomerUpdateRequest;
import com.itc.demofullstack.customer.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerIntegrationTest  {

    @Autowired
    private WebTestClient webTestClient;

    private static final Random RANDOM = new Random();

    private static final String CUSTOMER_PATH = "/api/v1/customers";
    @Test
    void canRegisterACustomer() {
        // create registration request
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String name = fakerName.fullName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@amigoscode.com";
        int age = RANDOM.nextInt(1, 100);
        String password = "password";
        Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                name, email, password, age, gender
        );
        // send a post request
        String jwtToken = webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        // get all customers
        List<CustomerDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerDTO>() {
                })
                .returnResult()
                .getResponseBody();

        // make sure that customer is present
        int id = allCustomers.stream()
                .filter(customer -> customer.email().equals(email))
                .map(CustomerDTO::id)
                .findFirst()
                .orElseThrow();

        CustomerDTO exceptedCustomer = new CustomerDTO(
                id,
                name,
                email,
                gender,
                age,
                List.of("ROLE_USER"),
                email
        );

        assertThat(allCustomers).contains(exceptedCustomer);

        // get customer by id
        webTestClient.get()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<CustomerDTO>() {})
                .isEqualTo(exceptedCustomer);

    }

    @Test
    void canDeleteCustomer() {
        // create registration request
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String name = fakerName.fullName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@itc.ac.id";
        int age = RANDOM.nextInt(1, 100);
        String password = "password";
        Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                name, email, password, age, gender
        );

        CustomerRegistrationRequest request2 = new CustomerRegistrationRequest(
                name, email + ".uk", password, age, gender
        );

        // send a post request to create customer 1
        webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // send a post request to create customer 2
        String jwtToken = webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request2), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        // get all customers
        List<CustomerDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerDTO>() {
                })
                .returnResult()
                .getResponseBody();

        int id = allCustomers.stream()
                .filter(customer -> customer.email().equals(email))
                .map(CustomerDTO::id)
                .findFirst()
                .orElseThrow();

        // customer 2 delete customer 1
        webTestClient.delete()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        // customer 2 get customer 1 by id
        webTestClient.get()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void canUpdateCustomer() {
        // create registration request
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String name = fakerName.fullName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@itc.ac.id";
        int age = RANDOM.nextInt(1, 100);
        String password = "password";
        Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                name, email, password, age, gender
        );

        // send a post request
        String jwtToken = webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        // get all customers
        List<CustomerDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerDTO>() {
                })
                .returnResult()
                .getResponseBody();


        int id = allCustomers.stream()
                .filter(customer -> customer.email().equals(email))
                .map(CustomerDTO::id)
                .findFirst()
                .orElseThrow();

        // update customer

        String newName = "Ali";

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                newName, null, null, null, null
        );

        webTestClient.put()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateRequest), CustomerUpdateRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // get customer by id
        CustomerDTO updatedCustomer = webTestClient.get()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CustomerDTO.class)
                .returnResult()
                .getResponseBody();

        CustomerDTO expected = new CustomerDTO(
                id, newName, email, gender, age, List.of("ROLE_USER"), email
        );

        assertThat(updatedCustomer).isEqualTo(expected);
    }
}