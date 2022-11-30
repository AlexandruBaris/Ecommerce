package com.alexandru.springbootecommerce.validation;

import com.alexandru.springbootecommerce.util.MessageGenerator;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class FirstnameValidator implements ConstraintValidator<Firstname, String> {

    private final MessageGenerator messageGenerator;

    private static final String FIRSTNAME = "([a-zA-Z])*";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int min = 3;
        int max = 22;
        if (SizeValidator.isInvalid(s, min, max)) {
            messageGenerator.generateSizeMessage(constraintValidatorContext, min, max, "First Name");
            return false;
        }
        return PatternValidator.isValid(s, FIRSTNAME);
    }
}
