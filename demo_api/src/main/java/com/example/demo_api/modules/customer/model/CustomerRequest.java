package com.example.demo_api.modules.customer.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest implements Serializable {
    @NotBlank
    @Size(min = 4, max = 50)
    private String email;

    @NotBlank
    @Size(min = 8, max = 50)
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message
//            = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special " +
//            "character.")
    private String password;

}
