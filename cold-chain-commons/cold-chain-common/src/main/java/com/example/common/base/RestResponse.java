package com.example.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = -1969217460222610389L;

    private String code;
    private String message;
    private String detailMessage;
    private T result;

    /**
     * 分布式链路
     */
    private String traceId;

    /**
     * 分布式链路跨度
     */
    private String spanId;

    /**
     * 当前环境
     */
    private String env;

    public RestResponse(IResponseCode iResponseCode) {
        this.code = iResponseCode.getCode();
        this.message = iResponseCode.getMessage();
        this.detailMessage = iResponseCode.getDetailMessage();

        // todo traceId spanId env
    }

    public RestResponse(IResponseCode iResponseCode, T result) {
        this.code = iResponseCode.getCode();
        this.message = iResponseCode.getMessage();
        this.detailMessage = iResponseCode.getDetailMessage();

        this.result = result;

        // todo traceId spanId env
    }

    public RestResponse(String code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;

        // todo traceId spanId env
    }

    public RestResponse(IResponseCode iResponseCode, String traceId, String spanId, String env) {
        this.code = iResponseCode.getCode();
        this.message = iResponseCode.getMessage();
        this.detailMessage = iResponseCode.getDetailMessage();
        this.traceId = traceId;
        this.spanId = spanId;
        this.env = env;
    }

    public static <T> RestResponse<T> error(IResponseCode iResponseCode) {
        return new RestResponse<T>(iResponseCode);
    }

    public static <T> RestResponse<T> error(IResponseCode iResponseCode, T result) {
        return new RestResponse<T>(iResponseCode, result);
    }

    public static <T> RestResponse<T> error(String code, String message, String detailMessage) {
        return new RestResponse<T>(code, message, detailMessage);
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse<T>(ResponseCode.SUCCESS);
    }

    public static <T> RestResponse<T> success(T result) {
        return new RestResponse<T>(ResponseCode.SUCCESS).setResult(result);
    }
}
