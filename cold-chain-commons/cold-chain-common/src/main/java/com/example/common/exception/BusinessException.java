package com.example.common.exception;

import com.example.common.base.ResponseCode;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4534536720709848760L;

    private ResponseCode responseCode;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ResponseCode responseCode) {
        super();
        this.responseCode = responseCode;
    }

    public BusinessException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public BusinessException(String message, Throwable cause, ResponseCode responseCode) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public BusinessException(Throwable cause, ResponseCode responseCode) {
        super(cause);
        this.responseCode = responseCode;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResponseCode responseCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
