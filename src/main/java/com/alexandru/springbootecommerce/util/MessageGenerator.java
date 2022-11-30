package com.alexandru.springbootecommerce.util;

import javax.validation.ConstraintValidatorContext;

public interface MessageGenerator {

    void generateSizeMessage(ConstraintValidatorContext constraintValidatorContext, int min, int max, String info);

    void generateMessage(ConstraintValidatorContext constraintValidatorContext, String message);

    void generateParameterizedMessage(ConstraintValidatorContext constraintValidatorContext, String message, String parameter);
}
