package org.ricky.common.password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/11
 * @className PasswordConfiguration
 * @desc
 */
@Configuration
public class PasswordConfiguration {

    @Bean
    public MyPasswordEncoder passwordEncoder() {
        return new SpringPasswordEncoder(new BCryptPasswordEncoder());
    }

}
