package org.ricky.core.common.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/26
 * @className ReturnId
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReturnId {

    String id;

    public static ReturnId returnId(String id) {
        return ReturnId.builder()
                .id(id)
                .build();
    }

    @Override
    public String toString() {
        return id;
    }

}
