package com.michaelfons.ferguson_backend_coding_challenge.repository;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer> {
}
