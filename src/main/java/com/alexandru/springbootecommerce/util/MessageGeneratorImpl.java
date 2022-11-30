package com.alexandru.springbootecommerce.util;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    @Override
    public void generateSizeMessage(ConstraintValidatorContext constraintValidatorContext, int min, int max, String info) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        HibernateConstraintValidatorContext hibernateConstraintValidatorContext =
                constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
        hibernateConstraintValidatorContext.addMessageParameter("min", min)
                .addMessageParameter("max", max).addMessageParameter("info", info)
                .buildConstraintViolationWithTemplate("{validation.size}")
                .addConstraintViolation();
    }

    public void generateMessage(ConstraintValidatorContext constraintValidatorContext, String message) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        HibernateConstraintValidatorContext hibernateConstraintValidatorContext =
                constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
        hibernateConstraintValidatorContext
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

    @Override
    public void generateParameterizedMessage(ConstraintValidatorContext constraintValidatorContext, String message, String parameter) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        HibernateConstraintValidatorContext hibernateConstraintValidatorContext =
                constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
        hibernateConstraintValidatorContext.addMessageParameter("parameter", parameter)
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
