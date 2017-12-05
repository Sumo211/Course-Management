package com.leon.study.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsCorrectNameValidator.class})
public @interface IsCorrectName {

    String message() default "Name is in correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;

}
