package com.alexandru.springbootecommerce.security.jwt;

import com.alexandru.springbootecommerce.entity.User;

public final class JwtUserFactory {
    public static JwtUser create(User user) {
        return JwtUser.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
