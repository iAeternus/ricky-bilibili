package org.ricky.core.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ricky.common.tracing.TracingService;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.ricky.core.common.exception.ErrorCodeEnum.SYSTEM_ERROR;
import static org.springframework.boot.web.error.ErrorAttributeOptions.defaults;
import static org.springframework.http.HttpStatus.valueOf;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private final ErrorAttributes errorAttributes;
    private final TracingService tracingService;

    @RequestMapping(value = ERROR_PATH)
    public ResponseEntity<?> handleError(WebRequest webRequest) {
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest);
        String error = (String) errorAttributes.get("myError");
        int status = (int) errorAttributes.get("status");
        String message = (String) errorAttributes.get("message");
        String path = (String) errorAttributes.get("path");
        String traceId = tracingService.currentTrackId();

        log.error("MyError access[{}]:{}.", path, message);
        MyError myErrorDetail = new MyError(SYSTEM_ERROR, status, error, path, traceId, null);

        return new ResponseEntity<>(myErrorDetail.toErrorResponse(), new HttpHeaders(), valueOf(status));
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return errorAttributes.getErrorAttributes(webRequest, defaults());
    }
}
