package com.alexandru.springbootecommerce.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LastnameValidator.class})
public @interface Lastname {

    String message() default "{validation.lastname}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
