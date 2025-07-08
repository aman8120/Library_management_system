package com.example.lib_mng_sys.dto.rules;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "email cannot be null")
@NotBlank(message = "email cannot be blank")
@jakarta.validation.constraints.Email(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",message = "Enter valid email id")
public @interface Email {
    String message() default "Invalid email!!";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
