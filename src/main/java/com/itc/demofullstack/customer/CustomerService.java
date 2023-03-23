package com.itc.demofullstack.customer;

import com.itc.demofullstack.exception.DuplicateResourceException;
import com.itc.demofullstack.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomers(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer with id [%s] not found".formatted(id)
                ));
    }

    public void addCustomer(
            CustomerRegistrationRequest customerRegistrationRequest) {

        // check if email exist
        String email = customerRegistrationRequest.email();
        if (customerDao.existPersonWithEmail(customerRegistrationRequest.email())) {
            throw new DuplicateResourceException(
                    "customer with email [%s] already exist".formatted(email)
            );
        }

        // add
        Customer customer = new Customer(
                        customerRegistrationRequest.name(),
                        customerRegistrationRequest.email(),
                        customerRegistrationRequest.age()
                );
        customerDao.insertCustomer(customer);
    }
}
