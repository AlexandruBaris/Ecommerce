package com.alexandru.springbootecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationExceptionResponse {

    private String field;

    private String message;
}
