package com.michaelfons.ferguson_backend_coding_challenge.repository;

import com.michaelfons.ferguson_backend_coding_challenge.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {
}
