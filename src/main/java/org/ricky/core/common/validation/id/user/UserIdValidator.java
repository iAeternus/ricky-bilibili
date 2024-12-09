package org.ricky.core.common.validation.id.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static org.ricky.core.common.utils.ValidationUtils.isBlank;


/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/27
 * @className MemberValidator
 * @desc
 */
public class UserIdValidator implements ConstraintValidator<UserId, String> {

    private static final Pattern PATTERN = Pattern.compile("^USR[0-9]{17,19}$");

    @Override
    public boolean isValid(String userId, ConstraintValidatorContext context) {
        if (isBlank(userId)) {
            return true;
        }

        return isUserId(userId);
    }

    public static boolean isUserId(String userId) {
        return PATTERN.matcher(userId).matches();
    }

}