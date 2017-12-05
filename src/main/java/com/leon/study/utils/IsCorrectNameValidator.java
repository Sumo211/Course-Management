package com.leon.study.utils;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsCorrectNameValidator implements ConstraintValidator<IsCorrectName, String> {

    private int min;

    private int max;

    @Override
    public void initialize(IsCorrectName constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Name cannot be empty").addConstraintViolation();
            return false;
        }

        if (value.length() < min || value.length() > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The length of name must be from " + min + " to " + max).addConstraintViolation();
            return false;
        }

        return true;
    }

}
