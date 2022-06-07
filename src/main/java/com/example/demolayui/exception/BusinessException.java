package com.example.demolayui.exception;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/7 10:03
 */
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;

    public BusinessException(){}
    public BusinessException(Integer code, String message) {
        super("{code:"+code+",message:"+message);
        this.code = code;
        this.message = message;
    }
    public BusinessException(Integer code, String message, Object... args) {
        super("{code:"+code+",message:" + String.format(message, args)+"}");
        this.code = code;
        this.message = String.format(message, args);
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
