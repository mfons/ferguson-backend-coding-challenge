package com.michaelfons.ferguson_backend_coding_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FergusonBackendCodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FergusonBackendCodingChallengeApplication.class, args);
	}

}
