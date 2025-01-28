package com.michaelfons.ferguson_backend_coding_challenge.services;

import com.michaelfons.ferguson_backend_coding_challenge.exceptions.DebuggingException;
import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.AccountCreationStatus;
import com.michaelfons.ferguson_backend_coding_challenge.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    // Note: the following field is used for debugging purposes and will break some tests.
    private boolean debugTransactional = false;

    public AccountService(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    @Transactional
    public AccountCreationStatus createAccount(Account account) {
        accountRepository.save(account);
        if (debugTransactional)  {
            System.out.println("Throwing exception to test transactional");
            throw new DebuggingException("Throwing exception to test transactional");
        }
        customerService.createCustomer(account.getCustomer());
        return AccountCreationStatus.SUCCESS;
    }
}
