package com.yang.mq.enums;

import lombok.Getter;

/**
 * @author Yang
 * @version 1.0.0
 * @description 返回状态码
 * @createTime 2022年08月05日 17:14:00
 */
@Getter
public enum EnumResultCode {

    //1000系列通用错误
    SUCCESS(1000, "操作成功"),
    FAILED(1001, "接口错误"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    ERROR(1003, "未知错误"),

    //2000系列用户错误
    USER_NOT_EXIST(2000,"用户不存在"),
    USER_LOGIN_FAIL(2001,"用户名或密码错误"),
    USER_NOT_LOGIN(2002,"用户还未登录,请先登录"),
    NO_PERMISSION(2003,"权限不足,请联系管理员");

    private final int code;
    private final String msg;

    EnumResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
