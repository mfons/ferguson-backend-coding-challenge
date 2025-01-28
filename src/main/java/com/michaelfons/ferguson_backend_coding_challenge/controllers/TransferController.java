package com.michaelfons.ferguson_backend_coding_challenge.controllers;

import com.michaelfons.ferguson_backend_coding_challenge.model.TransferResults;
import com.michaelfons.ferguson_backend_coding_challenge.services.AccountService;
import com.michaelfons.ferguson_backend_coding_challenge.services.TransferService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling money transfer-related requests
 * @see TransferService
 */
@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer/{sourceAccount}/{targetAccount}/{amount}")
    public TransferResults transfer(@PathVariable Integer sourceAccount, @PathVariable Integer targetAccount, @PathVariable Double amount) {
        return transferService.transfer(sourceAccount, targetAccount, amount);
    }
}
