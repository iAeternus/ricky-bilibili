package org.ricky.core.common.validation.string;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.ricky.core.common.utils.ValidationUtils.isBlank;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/11/18
 * @className ChineseCharLimitValidator
 * @desc
 */
public class ChineseCharLimitValidator implements ConstraintValidator<ChineseCharLimit, String> {

    private static final Pattern PATTERN = Pattern.compile("[\\u4E00-\\u9FFF]");
    private int min;
    private int max;

    @Override
    public void initialize(ChineseCharLimit chineseCharLimit) {
        this.min = chineseCharLimit.min();
        this.max = chineseCharLimit.max();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (isBlank(str)) {
            return true;
        }

        int chineseCharCount = chineseCharCount(str);
        return chineseCharCount >= min && chineseCharCount <= max;
    }

    public static int chineseCharCount(String str) {
        Matcher matcher = PATTERN.matcher(str);
        int chineseCharCount = 0;
        while (matcher.find()) {
            chineseCharCount++;
        }
        return chineseCharCount;
    }

}
