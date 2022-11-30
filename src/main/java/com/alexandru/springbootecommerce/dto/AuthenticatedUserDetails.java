package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Role;
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
    private String username;
    private String firstName;
    private String lastName;
    private String token;
    private Set<Role> roles = new HashSet<>();
}
