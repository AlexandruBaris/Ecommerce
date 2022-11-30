package com.alexandru.springbootecommerce.validation;

import com.alexandru.springbootecommerce.util.MessageGenerator;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private final MessageGenerator messageGenerator;

    private static final String PASSWORD = "^(?=.*([a-z]|[A-Z]))(?=.*[!|[.]@#&()–_\\[{}\\]:;'', ?/*~$^+=<>])[^\\s]*$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int min = 8;
        int max = 22;

        if (SizeValidator.isInvalid(s, min, max)) {
            messageGenerator.generateSizeMessage(constraintValidatorContext, min, max, "Password");
            return false;
        }
        return PatternValidator.isValid(s, PASSWORD);
    }
}
