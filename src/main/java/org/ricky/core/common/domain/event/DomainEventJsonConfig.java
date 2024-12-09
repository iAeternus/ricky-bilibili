package org.ricky.core.common.domain.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.lang.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/5
 * @className DomainEventJsonConfig
 * @desc 领域事件序列化配置注解
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes(value = {
        // @JsonSubTypes.Type(value = AppAttributesCreatedEvent.class, name = "ATTRIBUTES_CREATED"),
        // TODO add here...
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface DomainEventJsonConfig {
}
