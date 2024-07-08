package io.github.niwin92.brandpay.common.web;

import io.github.niwin92.brandpay.common.response.ResponseData;
import io.github.niwin92.brandpay.common.response.Result;
import io.github.niwin92.brandpay.common.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

/**
 * RestController 관련 예외처리
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class CommonRestControllerAdvice {

    /**
     * IllegalArgumentException(400) ExceptionHandler
     * @param ex IllegalArgumentException
     * @return ResponseEntity
     */
    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<ResponseData> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseData responseData = new ResponseData();
        responseData.setResult(Result.builder().code(ResultCode.ERROR400.getText()).message(HttpStatus.BAD_REQUEST.toString()).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    /**
     * AuthenticationException(401) ExceptionHandler
     * @param ex AuthenticationException
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler({AuthenticationException.class})
    protected ResponseEntity<ResponseData> handleAuthenticationException(final AuthenticationException ex, final WebRequest request) {
        ResponseData responseData = new ResponseData();
        responseData.setResult(Result.builder().code(ResultCode.ERROR401.getText()).message(HttpStatus.UNAUTHORIZED.toString()).build());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
    }

    /**
     * AccessDeniedException(403) ExceptionHandler
     * @param ex AccessDeniedException
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<ResponseData> handleAccessDeniedException(final AccessDeniedException ex, final WebRequest request) {
        ResponseData responseData = new ResponseData();
        responseData.setResult(Result.builder().code(ResultCode.ERROR403.getText()).message(HttpStatus.FORBIDDEN.toString()).build());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseData);
    }

    /**
     * Exception ExceptionHandler
     * @param ex Exception
     * @param request HttpServletRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseData> handleAllUncaughtException(Exception ex, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String message;
        if(ObjectUtils.isEmpty(ex.getMessage())){
            message = request.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR").toString();
        } else {
            message = ex.getMessage();
        }
        responseData.setResult(Result.builder().code(ResultCode.ERROR500.getText()).message(message).build());

        return ResponseEntity.status(getStatus(request)).body(responseData);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

