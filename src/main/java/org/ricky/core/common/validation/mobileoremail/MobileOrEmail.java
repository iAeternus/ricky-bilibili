package org.ricky.core.common.validation.mobileoremail;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/23
 * @className MobileOrEmail
 * @desc 手机号或邮箱
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = MobileOrEmailValidator.class)
public @interface MobileOrEmail {

    String message() default "Mobile or email format is incorrect.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
