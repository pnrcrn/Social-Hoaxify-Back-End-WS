package com.socialhoaxify.wsfs.annotations;

import org.hibernate.validator.internal.constraintvalidators.hv.UniqueElementsValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
//1
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy ={UniqueUsernameValidator.class})
public @interface UniqueUsername {
    java.lang.String message() default "{Username must be unique}";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends javax.validation.Payload>[] payload() default {};


}
