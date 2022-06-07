package com.example.demolayui.exception;

import lombok.Data;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/7 10:04
 */
public enum ExceptionEnum {
    BAD_REQUEST(400, "%s"),
    UNAUTHORIZED(401, "登录凭证过期"),
    FORBINDEN(403, "没有访问权限"),
    NOT_FOUND(404, "资源找不到"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务器正忙，请稍后再试"),

    UNKNOWN(10000, "未知异常！"),
    IS_NOT_NULL(10001, "%s不能为空");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
