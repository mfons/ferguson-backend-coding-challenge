package com.michaelfons.ferguson_backend_coding_challenge.controllers;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.TransferHistory;
import com.michaelfons.ferguson_backend_coding_challenge.services.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public TransferHistory transfer(@PathVariable Integer sourceAccount, @PathVariable Integer targetAccount, @PathVariable Double amount) {
        return transferService.transfer(sourceAccount, targetAccount, amount);
    }

    @GetMapping("/reports/transferHistoryByAccount/{sourceAccount}")
    public List<TransferHistory> getTransferHistoryAccount(@PathVariable Integer sourceAccount) {
        return transferService.getTransferHistory(sourceAccount);
    }
}
