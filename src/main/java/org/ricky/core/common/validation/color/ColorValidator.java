package org.ricky.core.common.validation.color;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ricky.core.common.constants.MyRegexConstants;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/27
 * @className ColorValidator
 * @desc
 */
public class ColorValidator implements ConstraintValidator<Color, String> {

    /**
     * 校验RGBA颜色<br>
     * RGBA颜色是一种包含红色（R）、绿色（G）、蓝色（B）和透明度（A）值的颜色表示方式<br>
     * 例如rgba(255, 0, 0, 0.5)<br>
     */
    private static final Pattern RGBA_COLOR_PATTERN = Pattern.compile(MyRegexConstants.RGBA_COLOR_PATTERN, CASE_INSENSITIVE);

    /**
     * 校验十六进制颜色（HEX颜色）<br>
     * 十六进制颜色代码是一种通过六位十六进制数来表示颜色的方法<br>
     * 例如#FF0000<br>
     */
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile(MyRegexConstants.HEX_COLOR_PATTERN, CASE_INSENSITIVE);

    /**
     * 校验RGB颜色<br>
     * RGB颜色是一种通过组合红色（R）、绿色（G）和蓝色（B）的不同强度来生成颜色的方法<br>
     * 例如rgb(255, 0, 0)<br>
     */
    private static final Pattern RGB_COLOR_PATTERN = Pattern.compile(MyRegexConstants.RGB_COLOR_PATTERN, CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isBlank(value)) {
            return true;
        }

        return RGBA_COLOR_PATTERN.matcher(value).matches() ||
                HEX_COLOR_PATTERN.matcher(value).matches() ||
                RGB_COLOR_PATTERN.matcher(value).matches();
    }
}
