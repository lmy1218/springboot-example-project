package com.yang.mq.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Yang
 * @version 1.0.0
 * @description 消息消费记录
 * @createTime 2022年08月08日 10:21:00
 */
@Data
@TableName("mq_message_log")
public class MqMessageLog {

    @TableId(type = IdType.AUTO)
    private String id;

    private String messageId;

    private String content;

    private Date processTime;

    private Date sendTime;

}
