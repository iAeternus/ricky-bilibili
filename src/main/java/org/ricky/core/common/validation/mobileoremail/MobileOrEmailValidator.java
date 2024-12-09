package org.ricky.core.common.validation.mobileoremail;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.ricky.core.common.constants.MyRegexConstants;

import java.util.regex.Pattern;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/23
 * @className MobileOrEmailValidator
 * @desc 手机号或邮箱校验器
 */
public class MobileOrEmailValidator implements ConstraintValidator<MobileOrEmail, String> {

    private static final Pattern MOBILE_PATTERN = Pattern.compile(MyRegexConstants.MOBILE_PATTERN);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(MyRegexConstants.EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        return value.length() <= 50 && (MOBILE_PATTERN.matcher(value).matches() || EMAIL_PATTERN.matcher(value).matches());
    }
}
