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
public class TransferServiceTest {
    @Autowired
    private TransferService transferService;
    @MockitoBean
    private AccountRepository accountRepository;
    private Integer sourceAccount;
    private Integer targetAccount;
    private Double amount;

    @BeforeEach
    void setUp() {
        sourceAccount = 1;
        targetAccount = 2;
        amount = 50.00;
    }

//    @Test
//    void aCustomerWantsATransfer_theTransferFunctionalityIsInvoked_() {
//        // Arrange
//        var newAccount = new Account(1, aCustomer, initialDeposit);
//        when(accountRepository.save(any(Account.class))).thenReturn(newAccount);
//
//        // Act
//        var result = transferService.transfer(sourceAccount, targetAccount, amount);
//
//        // Assert
//        assertEquals(result, AccountCreationStatus.SUCCESS);
//        verify(accountRepository).save(any(Account.class));
//    }

}
