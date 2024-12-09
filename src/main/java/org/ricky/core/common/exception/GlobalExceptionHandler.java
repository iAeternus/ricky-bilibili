package org.ricky.core.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ricky.common.tracing.TracingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.toImmutableMap;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ricky.core.common.exception.ErrorCodeEnum.SYSTEM_ERROR;
import static org.ricky.core.common.exception.MyException.*;
import static org.springframework.http.HttpStatus.valueOf;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private static final List<Integer> WARN_CODES = List.of(400, 401, 403, 426, 429);
    private final TracingService tracingService;

    @ResponseBody
    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> handleMryException(MyException ex, HttpServletRequest request) {
        if (WARN_CODES.contains(ex.getCode().getStatus())) {
            log.warn("Bcr warning: {}", ex.getMessage());
        } else {
            log.error("Bcr myError: {}", ex.getMessage());
        }

        return createErrorResponse(ex, request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<QErrorResponse> handleAccessDinedException(HttpServletRequest request) {
        return createErrorResponse(accessDeniedException(), request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<QErrorResponse> handleAuthenticationFailedException(HttpServletRequest request) {
        return createErrorResponse(authenticationException(), request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<QErrorResponse> handleInvalidRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> error = ex.getBindingResult().getFieldErrors().stream()
                .collect(toImmutableMap(FieldError::getField, fieldError -> {
                    String message = fieldError.getDefaultMessage();
                    return isBlank(message) ? "无错误提示。" : message;
                }, (field1, field2) -> field1 + "|" + field2));

        log.error("Method argument validation myError[{}]: {}", ex.getParameter().getParameterType().getName(), error);
        MyException exception = requestValidationException(error);
        return createErrorResponse(exception, request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler({ServletRequestBindingException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class})
    public ResponseEntity<QErrorResponse> handleServletRequestBindingException(Exception ex, HttpServletRequest request) {
        MyException exception = requestValidationException("message", "请求验证失败。");
        log.error("Request processing myError: {}", ex.getMessage());
        return createErrorResponse(exception, request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGeneralException(Throwable ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        String traceId = tracingService.currentTrackId();

        log.error("MyError access[{}]:", path, ex);
        MyError myError = new MyError(SYSTEM_ERROR, SYSTEM_ERROR.getStatus(), "系统错误。", path, traceId, null);
        return new ResponseEntity<>(myError.toErrorResponse(), new HttpHeaders(), HttpStatus.valueOf(SYSTEM_ERROR.getStatus()));
    }

    private ResponseEntity<QErrorResponse> createErrorResponse(MyException exception, String path) {
        String traceId = tracingService.currentTrackId();
        MyError myError = new MyError(exception, path, traceId);
        QErrorResponse representation = myError.toErrorResponse();
        return new ResponseEntity<>(representation, new HttpHeaders(), valueOf(representation.getMyError().getStatus()));
    }

}
