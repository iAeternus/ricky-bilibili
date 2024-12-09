package org.ricky.core.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.ricky.core.common.domain.marker.Response;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/25
 * @className QErrorResponse
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QErrorResponse implements Response {

    MyError myError;

}
