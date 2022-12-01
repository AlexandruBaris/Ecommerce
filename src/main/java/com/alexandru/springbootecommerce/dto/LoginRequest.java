package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.validation.Password;
import com.alexandru.springbootecommerce.validation.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Username
    private String username;
    @Password
    private String password;

}
