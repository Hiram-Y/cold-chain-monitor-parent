package com.example.common.exception.handler;

import com.example.common.base.ResponseCode;
import com.example.common.base.RestResponse;
import com.example.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RestResponse<Void> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("#handlerHttpRequestMethodNotSupportedException() the request mode is not supported: {}", e.getMessage(), e);

        return RestResponse.error(ResponseCode.SYSTEM_REQUEST_NOT_SUPPORTED);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public RestResponse<Void> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("#handlerHttpMediaTypeNotSupportedException() the media type is not supported: {}", e.getMessage(), e);

        return RestResponse.error(ResponseCode.SYSTEM_MEDIA_NOT_SUPPORTED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("#handleMethodArgumentNotValidException() parameter verification is abnormal: {}", e.getMessage(), e);

        return RestResponse.error(ResponseCode.USER_PARAMETER_ERROR.getCode(), ResponseCode.USER_PARAMETER_ERROR.getMessage(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public RestResponse<Void> handlerBusinessException(BusinessException e) {
        log.error("#handlerBusinessException() business exception: {}", e.getResponseCode().getDetailMessage(), e);

        return RestResponse.error(e.getResponseCode());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public RestResponse<Void> handlerGlobalException(Exception e) {
        log.error("#handlerBusinessException() system exception: {}", e.getMessage(), e);

        return RestResponse.error(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMessage(), e.getMessage());
    }
}
