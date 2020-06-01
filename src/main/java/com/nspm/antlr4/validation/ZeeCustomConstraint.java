package com.nspm.antlr4.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author zc
 * @date 2019/09/18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {ZeeCustomConstraintValidator.class}
)
@Documented
public @interface ZeeCustomConstraint {
    String message() default "ZeeSec custom constraints";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
