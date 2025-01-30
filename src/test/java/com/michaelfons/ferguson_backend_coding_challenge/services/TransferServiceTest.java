package com.michaelfons.ferguson_backend_coding_challenge.services;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.AccountCreationStatus;
import com.michaelfons.ferguson_backend_coding_challenge.model.TransferResults;
import com.michaelfons.ferguson_backend_coding_challenge.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TransferServiceTest {
    @Autowired
    private TransferService transferService;
    @MockitoBean
    private AccountRepository accountRepository;
    private Integer sourceAccountId;
    private Integer targetAccountId;
    @MockitoBean
    private Account sourceAccount;
    private Double sourceAccountBalance;
    @MockitoBean
    private Account targetAccount;
    private Double targetAccountBalance;

    private Double amount;

    @BeforeEach
    void setUp() {
        sourceAccountId = 1;
        targetAccountId = 2;
        amount = 50.00;
        sourceAccountBalance = 100.00;
        targetAccountBalance = 250.00;

    }

    /**
     * example Unit test for TransferService.transfer
     */
    @Test
    void aCustomerWantsATransfer_moneyIsTransferred_rightRepoMethodsInvoked() {
        // Arrange
        when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.ofNullable(sourceAccount));
        when(accountRepository.findById(targetAccountId)).thenReturn(Optional.ofNullable(targetAccount));
        doNothing().when(sourceAccount).setBalance(sourceAccountBalance - amount);
        doNothing().when(targetAccount).setBalance(targetAccountBalance + amount);
        Account alteredSourceAccount = new Account(sourceAccountId, null, sourceAccountBalance - amount);
        Account alteredTargetAccount = new Account(targetAccountId, null, targetAccountBalance + amount);
        when(accountRepository.save(sourceAccount)).thenReturn(alteredSourceAccount);
        when(accountRepository.save(targetAccount)).thenReturn(alteredTargetAccount);
        when(sourceAccount.getId()).thenReturn(sourceAccountId);
        when(targetAccount.getId()).thenReturn(targetAccountId);
        when(sourceAccount.getBalance()).thenReturn(sourceAccountBalance).thenReturn(sourceAccountBalance).thenReturn(alteredSourceAccount.getBalance()).thenReturn(alteredSourceAccount.getBalance());
        when(targetAccount.getBalance()).thenReturn(targetAccountBalance).thenReturn(alteredTargetAccount.getBalance()).thenReturn(alteredTargetAccount.getBalance());

        // Act
        var result = transferService.transfer(sourceAccountId, targetAccountId, amount);

        // Assert
        assertEquals(result, new TransferResults(1, sourceAccountId, sourceAccountBalance, sourceAccountBalance - amount,
                targetAccountId, targetAccountBalance, targetAccountBalance + amount));
        verify(accountRepository).findById(sourceAccountId);
        verify(accountRepository).findById(targetAccountId);
        verify(sourceAccount, times(4)).getBalance();
        verify(targetAccount, times(3)).getBalance();
        verify(sourceAccount).setBalance(sourceAccountBalance - amount);
        verify(targetAccount).setBalance(targetAccountBalance + amount);
        verify(accountRepository).save(sourceAccount);
        verify(accountRepository).save(targetAccount);
        verify(sourceAccount).getId();
        verify(targetAccount).getId();

    }

}
