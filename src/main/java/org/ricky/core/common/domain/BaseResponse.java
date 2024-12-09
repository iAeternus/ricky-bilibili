package org.ricky.core.common.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.ricky.core.common.domain.marker.Response;
import org.springframework.http.HttpStatus;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/22
 * @className BaseResponse
 * @desc 统一响应体
 */
@Deprecated
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> implements Response {

    /**
     * 响应状态码
     */
    Integer code;

    /**
     * 响应信息
     */
    String message;

    /**
     * 响应数据
     */
    T data;

    public static <T> BaseResponse<T> response(Integer code, String message, T data) {
        return new BaseResponse<>(code, message, data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return response(HttpStatus.OK.value(), message, data);
    }

    public static <T> BaseResponse<T> success() {
        return success("success", null);
    }

    public static <T> BaseResponse<T> success(String message) {
        return success(message, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return success("success", data);
    }

    public static <T> BaseResponse<T> created(T data) {
        return response(HttpStatus.CREATED.value(), "created", data);
    }

    public static <T> BaseResponse<T> created() {
        return created(null);
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }

    public static <T> BaseResponse<T> error() {
        return error("fail", null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return error(message, null);
    }

    public static <T> BaseResponse<T> error(T data) {
        return error("fail", data);
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
