package com.orange.frame.common;

public class CommonResult<T> {
    private long code;
    private String message;
    private T data;
    public CommonResult() {
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public CommonResult(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取成功消息
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data){
        return getCommonResultByStatus(ResultStatus.SUCCESS,data);
    }

    /**
     * 根据状态获取消息
     * @param resultStatus
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> getCommonResultByStatus(ResultStatus resultStatus){
        return new CommonResult<T>(resultStatus, null);
    }
    public static <T> CommonResult<T> getCommonResultByStatus(ResultStatus resultStatus,T data){
        return new CommonResult<T>(resultStatus, data);
    }
}
