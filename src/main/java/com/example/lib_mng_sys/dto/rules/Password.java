package com.example.lib_mng_sys.dto.rules;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Pattern(regexp = "^[A-Za-z\\d@#$%^&+=!]{8,12}$")
public @interface Password {
    String message() default "Invalid Password. Password must contain at least one lower case, upper case, number,special character,minlength=8 and maxlength=12";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
