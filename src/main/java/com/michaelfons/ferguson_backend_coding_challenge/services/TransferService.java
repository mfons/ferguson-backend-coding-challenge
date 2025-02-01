package com.michaelfons.ferguson_backend_coding_challenge.services;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.TransferHistory;
import com.michaelfons.ferguson_backend_coding_challenge.repository.AccountRepository;
import com.michaelfons.ferguson_backend_coding_challenge.repository.TransferHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    // Note: the following field is used for debugging purposes and will break some tests.
    private final TransferHistoryRepository transferHistoryRepository;
    private final SequenceGenerator sequenceGenerator;

    public TransferService(AccountRepository accountRepository, TransferHistoryRepository transferHistoryRepository, SequenceGenerator sequenceGenerator) {
        this.accountRepository = accountRepository;
        this.transferHistoryRepository = transferHistoryRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Transactional
    public TransferHistory transfer(Integer sourceAccountId, Integer targetAccountId, double amount) {
        Account sourceAccount = accountRepository.findById(sourceAccountId).orElseThrow();
        Account targetAccount = accountRepository.findById(targetAccountId).orElseThrow();
        if (sourceAccount.getBalance() < amount) {
            System.out.println("Insufficient funds to complete transfer");
            return new TransferHistory(sourceAccount.getId(), sourceAccount.getBalance(), sourceAccount.getBalance(),
                    targetAccount.getId(), targetAccount.getBalance(), targetAccount.getBalance());
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        var transferHistory = new TransferHistory(sourceAccount.getId(), sourceAccount.getBalance() + amount, sourceAccount.getBalance(),
                targetAccount.getId(), targetAccount.getBalance() - amount, targetAccount.getBalance());

        transferHistoryRepository.save(transferHistory);
        return transferHistory;
    }

    public List<TransferHistory> getTransferHistory(Integer sourceAccount) {
        return transferHistoryRepository.findAllBySourceAccount(sourceAccount);
    }
}
