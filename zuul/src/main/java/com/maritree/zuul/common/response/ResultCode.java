package com.maritree.zuul.common.response;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    @JsonCreator
    public static ResultCode getItem(int code) {
        for (ResultCode item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}