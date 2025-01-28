package com.michaelfons.ferguson_backend_coding_challenge.controllers;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.AccountCreationStatus;
import com.michaelfons.ferguson_backend_coding_challenge.services.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller for handling account-related requests
 * @see AccountService
 */
@RestController
@RestControllerAdvice
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     *
     * @param account
     * @return a message indicating whether the account was created successfully
     * @see AccountService#createAccount(Account)
     * Sample data:
     * {
     * "id": 1,
     * "customer": {
     * "id": 1,
     * "firstName": "Michael",
     * "lastName": "Fons"
     * },
     * "balance": 1234.00
     * }
     *
     * {
     * "id": 2,
     * "customer": {
     * "id": 1,
     * "firstName": "Michael",
     * "lastName": "Fons"
     * },
     * "balance": 300.00
     * }
     * {
     * "id": 3,
     * "customer": {
     * "id": 2,
     * "firstName": "Leopoldina",
     * "lastName": "Toast"
     * },
     * "balance": 543.21
     * }
     */
    @PostMapping("/account")
    public String createAccount(@RequestBody Account account) {
        if (accountService.createAccount(account).equals(AccountCreationStatus.SUCCESS)) {
            return "Account created";
        } else {
            return "Account creation failed";
        }
    }


}
