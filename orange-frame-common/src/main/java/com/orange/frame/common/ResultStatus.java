package com.orange.frame.common;

public enum ResultStatus {
    // 成功状态码
    SUCCESS(200L,"请求成功"),
    // 认证失败
    AUTHENTICATION_FAILURE(401L,"认证失败");

    private Long code;
    private String message;
    private ResultStatus(Long code, String message){
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
