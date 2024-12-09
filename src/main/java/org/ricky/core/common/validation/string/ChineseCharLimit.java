package org.ricky.core.common.validation.string;

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
 * @date 2024/11/18
 * @className ChineseCharLimit
 * @desc
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = ChineseCharLimitValidator.class)
@Documented
public @interface ChineseCharLimit {

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "Chinese Characters size is out of range.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
