package com.michaelfons.ferguson_backend_coding_challenge.services;

import com.michaelfons.ferguson_backend_coding_challenge.exceptions.DebuggingException;
import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.AccountCreationStatus;
import com.michaelfons.ferguson_backend_coding_challenge.model.TransferResults;
import com.michaelfons.ferguson_backend_coding_challenge.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    // Note: the following field is used for debugging purposes and will break some tests.

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransferResults transfer(Integer sourceAccountId, Integer targetAccountId, double amount) {
        Account sourceAccount = accountRepository.findById(sourceAccountId).orElseThrow();
        Account targetAccount = accountRepository.findById(targetAccountId).orElseThrow();
        if (sourceAccount.getBalance() < amount) {
            System.out.println("Insufficient funds to complete transfer");
            return new TransferResults(1, sourceAccount.getId(), sourceAccount.getBalance(), sourceAccount.getBalance(),
                    targetAccount.getId(), targetAccount.getBalance(), targetAccount.getBalance());
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        return new TransferResults(1, sourceAccount.getId(), sourceAccount.getBalance() + amount, sourceAccount.getBalance(),
                targetAccount.getId(), targetAccount.getBalance() - amount, targetAccount.getBalance());
    }
}
