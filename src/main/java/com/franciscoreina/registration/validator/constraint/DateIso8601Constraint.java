package com.franciscoreina.registration.validator.constraint;

import com.franciscoreina.registration.validator.DateIso8601Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.franciscoreina.registration.constants.Messages.ERROR_INVALID_DATE;

@Documented
@Constraint(validatedBy = DateIso8601Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateIso8601Constraint {

    String message() default ERROR_INVALID_DATE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

