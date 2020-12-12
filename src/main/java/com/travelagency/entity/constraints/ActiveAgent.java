package com.travelagency.entity.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ActiveAgentValidator.class)
public @interface ActiveAgent {
    String message() default "{javax.validation.constraints.ActiveAgent.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
