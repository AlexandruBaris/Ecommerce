package com.alexandru.springbootecommerce.util;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class AuthenticatedUserFinder {
    public static String getUsername() {
        String username;
        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            username = "";
        }
        return username;
    }
}
