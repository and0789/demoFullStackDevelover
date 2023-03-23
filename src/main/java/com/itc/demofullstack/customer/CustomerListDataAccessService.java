package com.itc.demofullstack.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {


    private static List<Customer> customers;

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
}
