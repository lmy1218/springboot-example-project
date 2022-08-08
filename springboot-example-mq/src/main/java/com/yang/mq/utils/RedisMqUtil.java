package com.yang.mq.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yang.mq.constant.MqConstant;
import com.yang.mq.dao.domain.MqMessageLog;
import com.yang.mq.dao.mapper.MqMessageLogMapper;
import com.yang.mq.exception.BizException;
import com.yang.mq.pojo.dto.MessageInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.api.stream.StreamAddArgs;
import org.redisson.api.stream.StreamReadGroupArgs;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author Yang
 * @version 1.0.0
 * @description Steam工具
 * @createTime 2022年08月08日 10:26:00
 */
@Slf4j
@Component
public class RedisMqUtil {

    @Resource
    private RedissonClient redissonClient;
    @Resource private MqMessageLogMapper mqMessageLogMapper;


    /**
     * stream 发送消息到队列
     *
     * @param message
     */
    public void sendStreamMessage(String topic, String message) {
        RStream<String, String> stream = redissonClient.getStream(topic);
        MessageInfoDto messageInfoDto = new MessageInfoDto();
        messageInfoDto.setMessageId(UUID.randomUUID().toString());
        messageInfoDto.setContent(message);
        messageInfoDto.setSendTime(new Date());

        RFuture<StreamMessageId> addAsync = stream.addAsync(StreamAddArgs.entry("msg", JSON.toJSONString(messageInfoDto)));
        addAsync.exceptionally(e -> {
            log.error("发送消息异常，重试一次", e);
            stream.addAsync(StreamAddArgs.entry("msg", JSON.toJSONString(messageInfoDto)));
            return null;
        });
        log.info("发送消息成功");
    }

    /**
     * stream 消费者消费消息
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void consumerSteamMessage(String topic, String group, String consumer, Consumer<Object> consumerFunc) {
        RStream<String, String> stream = redissonClient.getStream(topic);
        List<StreamGroup> streamGroups = stream.listGroups();
        if (isNotExist(streamGroups, group)) {
            stream.createGroup(group, StreamMessageId.NEWEST);
        }
        while (true) {
            try {
                Map<StreamMessageId, Map<String, String>> messages = stream.readGroup(group, consumer);
                for (Map.Entry<StreamMessageId, Map<String, String>> entry : messages.entrySet()) {
                    Map<String, String> msgMap = entry.getValue();
                    log.info("Steam消费：" + msgMap);
                    String msg = msgMap.get("msg");
                    if (CollectionUtils.isEmpty(msgMap) || StrUtil.isBlank(msg)) {
                        continue;
                    }
                    MessageInfoDto messageInfoDto = JSON.parseObject(msg, MessageInfoDto.class);
                    Integer count = mqMessageLogMapper.selectCount(Wrappers.lambdaQuery(MqMessageLog.class)
                            .eq(MqMessageLog::getMessageId, messageInfoDto.getMessageId()));
                    if (count != null && count > 0) {
                        log.info("该消息已被消费：" + msg);
                        continue;
                    }

                    // 消息处理 TODO:
                    consumerFunc.accept(messageInfoDto.getContent());

                    MqMessageLog messageLog = new MqMessageLog();
                    messageLog.setMessageId(messageInfoDto.getMessageId());
                    messageLog.setContent(messageInfoDto.getContent());
                    messageLog.setSendTime(messageInfoDto.getSendTime());
                    messageLog.setProcessTime(new Date());
                    mqMessageLogMapper.insert(messageLog);
                    stream.ack(group, entry.getKey());
                }
            } catch (Exception e) {
                log.error("消费消息异常", e);
                throw new BizException("消费消息异常" + e.getMessage());
            }
        }

    }

    /**
     * 发送消息到队列头部
     *
     * @param message
     */
    public void sendListMessage(String topic, String message) {
        RBlockingDeque<String> blockingDeque = redissonClient.getBlockingDeque(topic);

        MessageInfoDto messageInfoDto = new MessageInfoDto();
        messageInfoDto.setMessageId(UUID.randomUUID().toString());
        messageInfoDto.setContent(message);
        messageInfoDto.setSendTime(new Date());
        try {
            blockingDeque.putFirst(JSON.toJSONString(messageInfoDto));
            log.info("将消息: {} 插入到队列。", message);
        }  catch (Exception e) {
            log.error("发送消息处理失败", e);
            throw new BizException("发送消息处理失败:" + e.getMessage());
        }
    }

    /**
     * 从队列尾部阻塞读取消息，若没有消息，线程就会阻塞等待新消息插入，防止 CPU 空转
     */
    @Transactional(rollbackFor = Exception.class)
    public void consumerListMessage(String topic, Consumer<Object> consumerFunc) {
        RBlockingDeque<String> blockingDeque = redissonClient.getBlockingDeque(topic);
        while (true) {
            try {
                String message = blockingDeque.takeLast();
                MessageInfoDto messageInfoDto = JSON.parseObject(message, MessageInfoDto.class);
                Integer count = mqMessageLogMapper.selectCount(Wrappers.lambdaQuery(MqMessageLog.class)
                        .eq(MqMessageLog::getMessageId, messageInfoDto.getMessageId()));
                if (count != null && count > 0) {
                    log.info("该消息已被消费：" + message);
                    continue;
                }

                log.info("List消费：从队列 {} 中读取到消息：{}.", topic, message);
                // 执行消费逻辑
                consumerFunc.accept(messageInfoDto.getContent());

                MqMessageLog messageLog = new MqMessageLog();
                messageLog.setMessageId(messageInfoDto.getMessageId());
                messageLog.setContent(messageInfoDto.getContent());
                messageLog.setSendTime(messageInfoDto.getSendTime());
                messageLog.setProcessTime(new Date());
                mqMessageLogMapper.insert(messageLog);
            } catch (Exception e) {
                log.error("消费消息处理失败", e);
                throw new BizException("消费消息处理失败:" + e.getMessage());
            }

        }
    }

    private boolean isNotExist(List<StreamGroup> streamGroups, String group) {
        if (CollectionUtil.isEmpty(streamGroups)) {
            return true;
        }

        StreamGroup streamGroup = streamGroups.stream().filter(item -> item.getName().equals(group)).findFirst().orElse(null);
        if (Objects.isNull(streamGroup)) {
            return true;
        }
        return false;
    }


}
