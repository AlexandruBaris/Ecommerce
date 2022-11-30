package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Role;
import com.alexandru.springbootecommerce.validation.Firstname;
import com.alexandru.springbootecommerce.validation.Lastname;
import com.alexandru.springbootecommerce.validation.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticatedUserDetails {

    private Long id;
    @Username
    private String username;
    @Firstname
    private String firstName;
    @Lastname
    private String lastName;
    private String token;
    private Set<Role> roles = new HashSet<>();
}
