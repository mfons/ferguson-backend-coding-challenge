package com.michaelfons.ferguson_backend_coding_challenge.services;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.AccountCreationStatus;
import com.michaelfons.ferguson_backend_coding_challenge.model.Customer;
import com.michaelfons.ferguson_backend_coding_challenge.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @MockitoBean
    private AccountRepository accountRepository;
    @MockitoBean
    private CustomerService customerService;

    @Test
    void initialDeposit_createAccountCalled_returnsSuccess() {
        // Arrange
        Double initialDeposit = 100.0;
        var aCustomer = new Customer(1, "John", "Doe");
        var newAccount = new Account(1, aCustomer, initialDeposit);
        when(accountRepository.save(any(Account.class))).thenReturn(newAccount);

        // Act
        var result = accountService.createAccount(newAccount);

        // Assert
        assertEquals(result, AccountCreationStatus.SUCCESS);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void newCustomer_createAccountAndNewCustomer_returnsSuccess() {
        // Arrange
        Double initialDeposit = 1234.00;
        var aCustomer = new Customer(2, "Fred", "Flintstone");
        var newAccount = new Account(2, aCustomer, initialDeposit);
        when(accountRepository.save(any(Account.class))).thenReturn(newAccount);
        when(customerService.createCustomer(any(Customer.class))).thenReturn(aCustomer);

        // Act
        var result = accountService.createAccount(newAccount);

        // Assert
        assertEquals(result, AccountCreationStatus.SUCCESS);
        verify(accountRepository).save(any(Account.class));
        verify(customerService).createCustomer(any(Customer.class));
    }
}
