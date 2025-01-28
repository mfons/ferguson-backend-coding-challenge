package com.michaelfons.ferguson_backend_coding_challenge.services;

import com.michaelfons.ferguson_backend_coding_challenge.model.Customer;
import com.michaelfons.ferguson_backend_coding_challenge.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @MockitoBean
    private CustomerRepository customerRepository;

    @Test
    void aCustomer_weWantThisCustomerInformationPersisted_UpdatesDatabase() {
        // Arrange
        var aCustomer = new Customer(1, "John", "Doe");
        when(customerRepository.save(any(Customer.class))).thenReturn(aCustomer);
        // Act
        var result = customerService.createCustomer(aCustomer);

        // Assert
        assertSame(result, aCustomer);
        verify(customerRepository).save(aCustomer);
    }
}
