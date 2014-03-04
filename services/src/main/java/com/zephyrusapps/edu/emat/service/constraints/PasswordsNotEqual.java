package com.zephyrusapps.edu.emat.service.constraints;

import com.zephyrusapps.edu.emat.service.constraints.validators.PasswordsNotEqualValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordsNotEqualValidator.class)
@Documented
public @interface PasswordsNotEqual {

    String message() default "PasswordsNotEqual";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String passwordFieldName() default "";

    String passwordVerificationFieldName() default "";
}