package com.michaelfons.ferguson_backend_coding_challenge.controllers;

import com.michaelfons.ferguson_backend_coding_challenge.model.Account;
import com.michaelfons.ferguson_backend_coding_challenge.model.AccountCreationStatus;
import com.michaelfons.ferguson_backend_coding_challenge.model.Customer;
import com.michaelfons.ferguson_backend_coding_challenge.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final Double INITIAL_DEPOSIT = 1_234.00D;

    private Account account;

    @MockitoBean
    private AccountService service;

    @BeforeEach
    void setUp() {
        account = new Account(1, new Customer(1, "Michael", "Fons"), INITIAL_DEPOSIT);
    }

    @Test
    void aCustomer_theyWantANewAccount_returns200() throws Exception {
        when(service.createAccount(account)).thenReturn(AccountCreationStatus.SUCCESS);
        this.mockMvc
                .perform(post("/account")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"customer\":{\"id\":1,\"firstName\":\"Michael\",\"lastName\":\"Fons\"}, \"balance\":"+ INITIAL_DEPOSIT + " }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Account created")));
        verify(service).createAccount(account);
    }

    @Test
    void aCustomer_theyWantANewAccountWithSystemError_returnsFailureMessage() throws Exception {
        when(service.createAccount(account)).thenReturn(AccountCreationStatus.FAILURE);
        this.mockMvc
                .perform(post("/account")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"customer\":{\"id\":1,\"firstName\":\"Michael\",\"lastName\":\"Fons\"}, \"balance\":"+ INITIAL_DEPOSIT + " }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Account creation failed")));
        verify(service).createAccount(account);
    }
}
