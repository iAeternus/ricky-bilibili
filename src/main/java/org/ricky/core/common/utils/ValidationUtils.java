package org.ricky.core.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/24
 * @className ValidationUtils
 * @desc 对象校验工具
 */
public class ValidationUtils {

    public static String nullIfBlank(String string) {
        if (StringUtils.isBlank(string)) {
            return null;
        }

        return string;
    }

    public static String requireNonBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(message);
        }
        return str;
    }

    public static <T> T requireNonNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }

    public static boolean isBlank(CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    public static boolean isNotBlank(CharSequence cs) {
        return StringUtils.isNotBlank(cs);
    }

    public static boolean isEmpty(Collection<?> coll) {
        return CollectionUtils.isEmpty(coll);
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return CollectionUtils.isNotEmpty(coll);
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static boolean nonNull(Object obj) {
        return Objects.nonNull(obj);
    }

    public static boolean equals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }

    public static boolean notEquals(Object obj1, Object obj2) {
        return !equals(obj1, obj2);
    }

}
