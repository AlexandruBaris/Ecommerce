package com.alexandru.springbootecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadCredentialsErrorResponse {
    private String message;
}
