package com.example.demo_api.modules.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private  UUID uuid;
    private String email;
    private String password;
}
