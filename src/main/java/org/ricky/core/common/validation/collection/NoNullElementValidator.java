package org.ricky.core.common.validation.collection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/27
 * @className NoNullElementValidator
 * @desc
 */
@SuppressWarnings("rawtypes")
public class NoNullElementValidator implements ConstraintValidator<NoNullElement, Collection> {

    @Override
    public boolean isValid(Collection collection, ConstraintValidatorContext constraintValidatorContext) {
        if (isEmpty(collection)) {
            return true;
        }

        return !collection.contains(null);
    }

}
