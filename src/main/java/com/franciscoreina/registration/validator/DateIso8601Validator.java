package com.franciscoreina.registration.validator;

import com.franciscoreina.registration.validator.constraint.DateIso8601Constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateIso8601Validator implements ConstraintValidator<DateIso8601Constraint, String> {

    @Override
    public void initialize(DateIso8601Constraint constraint) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext cxt) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
