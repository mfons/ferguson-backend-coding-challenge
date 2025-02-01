package com.michaelfons.ferguson_backend_coding_challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document
@Data
@NoArgsConstructor
public class TransferHistory {
    @Transient
    public static final String SEQUENCE_NAME = "transfer_history_sequence";

    @Id
    private BigInteger id = BigInteger.valueOf(-1);

    private Integer sourceAccount;

    private Double sourceBeginningBalance;
    private Double sourceEndingBalance;

    private Integer targetAccount;

    private Double targetBeginningBalance;
    private Double targetEndingBalance;

    public TransferHistory(Integer sourceAccount, Double sourceBeginningBalance, Double sourceEndingBalance, Integer targetAccount, Double targetBeginningBalance, Double targetEndingBalance) {
        this.sourceAccount = sourceAccount;
        this.sourceBeginningBalance = sourceBeginningBalance;
        this.sourceEndingBalance = sourceEndingBalance;
        this.targetAccount = targetAccount;
        this.targetBeginningBalance = targetBeginningBalance;
        this.targetEndingBalance = targetEndingBalance;
    }
}