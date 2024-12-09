package org.ricky.common.sensitiveword.infrastructure.utils;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/21
 * @className StrUtils
 * @desc 字符串工具类
 */
public class StrUtils {

    private StrUtils() {
    }

    /**
     * 判断字符串是否为空<br>
     * 判断为空的标准为长度为0或全部为不可见字符
     *
     * @param cs 字符串
     * @return true=空 false=不空
     */
    public static boolean isBlank(CharSequence cs) {
        int strlen = length(cs);
        if (strlen != 0) {
            for (int i = 0; i < strlen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取字符串长度
     *
     * @param cs 字符串
     * @return 如果字符串为空返回0，否则返回字符串长度
     */
    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

}
