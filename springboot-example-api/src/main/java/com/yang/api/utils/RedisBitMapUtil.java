package com.yang.api.utils;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 16:43:00
 */
@Component
public class RedisBitMapUtil {

    @Resource
    private RedisTemplate redisTemplate;

    private static final String ONLINE_STATUS = "online_status";

    /**
     * 设置在线状态
     *
     * @param userId
     * @param online
     */
    public void setOnlineStatus(int userId, boolean online) {
        redisTemplate.opsForValue().setBit(ONLINE_STATUS, userId, online);
    }

    /**
     * 统计在线人数
     *
     * @return
     */
    public Long getOnlineCount() {
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(ONLINE_STATUS.getBytes()));
    }


}
