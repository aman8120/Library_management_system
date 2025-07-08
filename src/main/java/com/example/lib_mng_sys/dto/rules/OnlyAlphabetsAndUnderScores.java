package com.example.lib_mng_sys.dto.rules;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "User name cannot be null")
@NotBlank(message = "User name cannot be blank")
@Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "User name can only contain Alphabets,Numbers and Underscore")
public @interface OnlyAlphabetsAndUnderScores {
    String message() default "Invalid field value, can only contain Alphabets,Numbers and Underscore";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
