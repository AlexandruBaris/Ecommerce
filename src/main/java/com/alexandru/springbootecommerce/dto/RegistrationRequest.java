package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.validation.Firstname;
import com.alexandru.springbootecommerce.validation.Lastname;
import com.alexandru.springbootecommerce.validation.Password;
import com.alexandru.springbootecommerce.validation.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @Firstname
    private String firstName;
    @Lastname
    private String lastName;
    @Username
    private String username;
    @Password
    private String password;

}
