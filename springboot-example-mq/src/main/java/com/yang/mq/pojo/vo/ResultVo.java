package com.yang.mq.pojo.vo;

import com.yang.mq.enums.EnumResultCode;
import lombok.Data;

/**
 * @author Yang
 * @version 1.0.0
 * @description 统一返回结构
 * @createTime 2022年08月05日 17:12:00
 */
@Data
public class ResultVo<T> {

    /**
     * 状态码，比如1000代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVo(T data) {
        this(EnumResultCode.SUCCESS, data);
    }

    public ResultVo(EnumResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

}
