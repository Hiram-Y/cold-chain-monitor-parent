package com.example.common.base;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public enum ResponseCode implements IResponseCode {
    /**
     * 操作正常
     */
    SUCCESS("00000", "success", "success"),

    /**
     * 用户错误
     */
    USER_CLIENT_ERROR("A0001", "用户客户端错误", "用户客户端错误"),
    USER_PARAMETER_ERROR("A1001", "用户参数不合法", "用户参数不合法"),
    USER_PUBLIC_KEY_ERROR("A1002", "用户密码不合法", "用户密码不合法, 怀疑伪造公钥"),
    USER_UNAUTHORIZED_ERROR("A1003", "用户未认证或认证过期", "用户未认证或认证过期"),
    USER_NOT_EXISTS_ERROR("A1004", "用户不存在", "用户不存在"),
    USER_EXISTS_ERROR("A1005", "用户以存在", "用户以存在"),
    USER_PASSWORD_ERROR("A1006", "用户密码错误", "用户密码错误"),
    USER_AUTHORIZATION_ERROR("A1007", "用户认证失败", "用户签名签发失败"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR("B0001", "系统执行失败, 请稍后重试", "系统执行错误, 发生未知异常"),
    SYSTEM_REQUEST_NOT_SUPPORTED("B1001", "接口不支持该请求方式", "接口不支持该请求方式"),
    SYSTEM_MEDIA_NOT_SUPPORTED("B1002", "接口不支持该媒体资源", "接口不支持该媒体资源"),

    /**
     * 第三方错误
     */
    THIRD_PARTY_ERROR("C0001", "系统执行失败, 请稍后重试", "第三方调用失败"),
    ;

    private final String code;
    private final String message;
    private final String detailMessage;

    ResponseCode(String code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDetailMessage() {
        return this.detailMessage;
    }
}