package org.ricky.common.password;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/11
 * @className MyPasswordEncoder
 * @desc 密码编码器
 */
public interface MyPasswordEncoder {

    /**
     * 编码
     *
     * @param rawPassword 原始密码
     * @return 编码后的密码
     */
    String encode(CharSequence rawPassword);

    /**
     * 判断原始密码与编码后的密码是否匹配
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 编码后的密码
     * @return true=匹配 false=不匹配
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);

}
