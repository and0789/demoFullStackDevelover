package com.itc.demofullstack.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao {

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> selectAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customerRepository.findById(id);
    }
}
