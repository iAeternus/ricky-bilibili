package org.ricky.core.common.validation.verficationcode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ricky.core.common.constants.MyRegexConstants.VERIFICATION_CODE_PATTERN;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/26
 * @className VerificationCodeValidator
 * @desc
 */
public class VerificationCodeValidator implements ConstraintValidator<VerificationCode, String> {

    private static final Pattern PATTERN = Pattern.compile(VERIFICATION_CODE_PATTERN);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isBlank(value)) {
            return true;
        }

        return PATTERN.matcher(value).matches();
    }
}
