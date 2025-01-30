package com.michaelfons.ferguson_backend_coding_challenge.controllers;

import com.michaelfons.ferguson_backend_coding_challenge.model.TransferHistory;
import com.michaelfons.ferguson_backend_coding_challenge.services.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferController.class)
@AutoConfigureMockMvc
public class TransferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransferService service;

    private TransferHistory transferHistory;
    public static final double TRANSFER_AMOUNT = 50.00;
    Integer sourceAccount;
    Integer targetAccount;
    Double amount = TRANSFER_AMOUNT;

    @BeforeEach
    void setUp() {
        transferHistory = new TransferHistory(
                1,
                3, 100.00, 50.00,
                2, 250.00, 300.00
        );
        sourceAccount = 3;
        targetAccount = 2;
    }

    @Test
    void aCustomer_theyWantToTransferMoneyToATargetAccount_responseIsCorrect() throws Exception {
        when(service.transfer(sourceAccount, targetAccount, amount)).thenReturn(transferHistory);
        this.mockMvc
                .perform(post("/transfer/" + sourceAccount + "/" + targetAccount + "/" + amount)
                        .accept(MediaType.APPLICATION_JSON)
//                        .content(String.format("{\"targetAccount\":%d, \"amount\":%f }",
//                                targetAccount, TRANSFER_AMOUNT))
//                        .contentType(MediaType.APPLICATION_JSON))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(String.format("{ \"id\": %d, \"sourceAccount\":%d, \"sourceBeginningBalance\":%f, \"sourceEndingBalance\":%f, \"targetAccount\":%d, \"targetBeginningBalance\":%f, \"targetEndingBalance\":%f }",
                        transferHistory.getId(), transferHistory.getSourceAccount(),
                        transferHistory.getSourceBeginningBalance(), transferHistory.getSourceEndingBalance(),
                        transferHistory.getTargetAccount(), transferHistory.getTargetBeginningBalance(), transferHistory.getTargetEndingBalance())));
        verify(service).transfer(sourceAccount, targetAccount, amount);
    }

    @Test
    void aCustomer_theyWantToSeeTransferHistory_responseShowsHistory() {

    }

}
