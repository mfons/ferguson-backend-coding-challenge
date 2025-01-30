package com.michaelfons.ferguson_backend_coding_challenge.repository;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.TransferHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferHistoryRepository extends MongoRepository<TransferHistory, Integer> {
    List<TransferHistory> findAllBySourceAccount(Integer sourceAccount);
}
