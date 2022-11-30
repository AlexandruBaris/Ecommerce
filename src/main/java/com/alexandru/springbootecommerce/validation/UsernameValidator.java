package com.alexandru.springbootecommerce.validation;

import com.alexandru.springbootecommerce.util.MessageGenerator;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final MessageGenerator messageGenerator;
    private static final String USERNAME = "^[a-zA-Z0-9]+[.]?[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z]+$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int min = 8;
        int max = 30;
        if (SizeValidator.isInvalid(s, min, max)) {
            messageGenerator.generateSizeMessage(constraintValidatorContext, min, max, "Username");
            return false;
        }
        return PatternValidator.isValid(s, USERNAME);
    }
}
