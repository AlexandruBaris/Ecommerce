package com.alexandru.springbootecommerce.validation;

import com.alexandru.springbootecommerce.util.MessageGenerator;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final MessageGenerator messageGenerator;
    private static final String USERNAME = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";;

    //^[a-zA-Z0-9]+[.]?[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z]+$

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int min = 8;
        int max = 50;
        if (SizeValidator.isInvalid(s, min, max)) {
            messageGenerator.generateSizeMessage(constraintValidatorContext, min, max, "Username");
            return false;
        }
        return PatternValidator.isValid(s, USERNAME);
    }
}
