package com.michaelfons.ferguson_backend_coding_challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private Integer id;

    private String firstName;
    private String lastName;

}
