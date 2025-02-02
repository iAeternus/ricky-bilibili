package org.ricky.core.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/9
 * @className MapUtils
 * @desc Map工具类
 */
public class MapUtils {

    private MapUtils() {
    }

    public static Map<String, Object> mapOf(String key, Object value) {
        HashMap<String, Object> map = new HashMap<>(2, 0.9f);
        map.put(key, value);
        return map;
    }

    public static Map<String, Object> mapOf(String key1, Object value1,
                                            String key2, Object value2) {
        HashMap<String, Object> map = new HashMap<>(3, 0.9f);
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }

    public static Map<String, Object> mapOf(String key1, Object value1,
                                            String key2, Object value2,
                                            String key3, Object value3) {
        HashMap<String, Object> map = new HashMap<>(4, 0.9f);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        return map;
    }

    public static Map<String, Object> mapOf(String key1, Object value1,
                                            String key2, Object value2,
                                            String key3, Object value3,
                                            String key4, Object value4) {
        HashMap<String, Object> map = new HashMap<>(5, 0.9f);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        return map;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return org.apache.commons.collections4.MapUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return org.apache.commons.collections4.MapUtils.isNotEmpty(map);
    }

}
