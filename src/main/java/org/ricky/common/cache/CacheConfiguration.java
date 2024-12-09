package org.ricky.common.cache;

import org.ricky.core.common.utils.MyObjectMapper;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_FINAL;
import static java.time.Duration.ofDays;
import static java.time.Duration.ofSeconds;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/14
 * @className CacheConfiguration
 * @desc
 */
@Configuration(proxyBeanMethods = false)
public class CacheConfiguration {

    /**
     * 缓存名称前缀
     */
    private static final String CACHE_PREFIX = "Cache:";

    @Bean
    public RedisCacheManagerBuilderCustomizer redisBuilderCustomizer(MyObjectMapper objectMapper) {
        MyObjectMapper defaultObjectMapper = new MyObjectMapper();
        defaultObjectMapper.activateDefaultTyping(defaultObjectMapper.getPolymorphicTypeValidator(), NON_FINAL, PROPERTY);
        GenericJackson2JsonRedisSerializer defaultSerializer = new GenericJackson2JsonRedisSerializer(defaultObjectMapper);

        // var appSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, App.class);
        // var groupSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Group.class);
        // var groupHierarchySerializer = new Jackson2JsonRedisSerializer<>(objectMapper, GroupHierarchy.class);
        // var deptHierarchySerializer = new Jackson2JsonRedisSerializer<>(objectMapper, DepartmentHierarchy.class);
        // var memberSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, User.class);
        // var tenantSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Tenant.class);

        return builder -> builder.cacheDefaults(defaultCacheConfig()
                        .prefixCacheNameWith(CACHE_PREFIX)
                        .serializeValuesWith(fromSerializer(defaultSerializer))
                        .entryTtl(ofDays(1)));

        // .withCacheConfiguration(TENANT_APPS_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(defaultSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(TENANT_DEPARTMENTS_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(defaultSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(APP_GROUPS_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(defaultSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(APP_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(appSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(GROUP_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(groupSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(GROUP_HIERARCHY_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(groupHierarchySerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(DEPARTMENT_HIERARCHY_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(deptHierarchySerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(MEMBER_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(memberSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(TENANT_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(tenantSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(API_TENANT_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(tenantSerializer))
        //         .entryTtl(ofDays(7)))
        // .withCacheConfiguration(OPEN_ASSIGNMENT_PAGES_CACHE, defaultCacheConfig()
        //         .prefixCacheNameWith(CACHE_PREFIX)
        //         .serializeValuesWith(fromSerializer(defaultSerializer))
        //         .entryTtl(ofHours(12)));
    }
}
