package org.ricky.core.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/10
 * @className CommonProperties
 * @desc 通用配置项
 */
@Data
@Component
@Validated
@ConfigurationProperties("my.common")
public class CommonProperties {

    /**
     * 是否启用流控
     */
    private boolean limitRate;

}
