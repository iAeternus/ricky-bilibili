package org.ricky.common.password;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/11
 * @className SpringPasswordEncoder
 * @desc 使用Spring Security的编码器
 */
@RequiredArgsConstructor
public class SpringPasswordEncoder implements MyPasswordEncoder {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
