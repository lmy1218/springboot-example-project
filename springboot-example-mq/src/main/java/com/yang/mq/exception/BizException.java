package com.yang.mq.exception;

import com.yang.mq.enums.EnumResultCode;
import lombok.Getter;

/**
 * @author Yang
 * @version 1.0.0
 * @description 业务异常
 * @createTime 2022年08月05日 17:40:00
 */
@Getter
public class BizException extends RuntimeException{

    private final int code;
    private final String msg;

    public BizException() {
        this(EnumResultCode.FAILED);
    }

    public BizException(EnumResultCode failed) {
        this.code=failed.getCode();
        this.msg=failed.getMsg();
    }

    public BizException(String msg) {
        this.msg = msg;
        this.code = EnumResultCode.FAILED.getCode();
    }

    public BizException(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }


}
