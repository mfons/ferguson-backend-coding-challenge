package com.michaelfons.ferguson_backend_coding_challenge.services;

import com.michaelfons.ferguson_backend_coding_challenge.model.Customer;
import com.michaelfons.ferguson_backend_coding_challenge.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer aCustomer) {
        var newCustomer = customerRepository.save(aCustomer);
        return newCustomer;
    }
}
