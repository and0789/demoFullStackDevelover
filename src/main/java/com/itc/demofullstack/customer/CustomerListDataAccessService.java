package com.itc.demofullstack.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {


    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer andre = new Customer(
                1,
                "Andre Septian",
                "andre@gmail.com",
                21
        );
        customers.add(andre);
        Customer fatimah = new Customer(
                2,
                "Fatimah Azzahra",
                "fatimah@gmail.com",
                20
        );
        customers.add(fatimah);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customers.stream()
                .allMatch(customer -> customer.getEmail().equals(email));
    }

    @Override
    public boolean existPersonWithId(Integer customerId) {
        return customers.stream()
                .anyMatch(customer -> customer.getId().equals(customerId));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }
}
