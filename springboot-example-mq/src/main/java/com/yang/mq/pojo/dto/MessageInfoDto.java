package com.yang.mq.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Yang
 * @version 1.0.0
 * @description TODO
 * @createTime 2022年08月04日 17:51:00
 */
@Data
public class MessageInfoDto {

    private String messageId;

    private String content;

    private Date sendTime;
}
