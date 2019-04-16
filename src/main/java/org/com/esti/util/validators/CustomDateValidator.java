package org.com.esti.util.validators;


import org.com.esti.util.validators.annotations.DateValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CustomDateValidator implements ConstraintValidator<DateValidation, LocalDate> {

    @Override
    public void initialize(DateValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return localDate.isBefore(today);
    }
}
