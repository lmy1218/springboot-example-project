package com.yang.mq.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 16:29:00
 */
@Slf4j
@Component
public class RedisScanUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取一批指定前缀的key eg: key:* 获取所有key:开头的key
     *
     * @param pattern key匹配正则
     * @param count   一次获取数目
     * @return
     */
    public Set<String> scan(String pattern, int count) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            try (Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder()
                    .match(pattern)
                    .count(count).build())) {
                while (cursor.hasNext()) {
                    keysTmp.add(new String(cursor.next(), StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return keysTmp;
        });
    }

    /**
     * 批量删除
     *
     * @param pattern key匹配正则
     * @param step    阶梯删除的数目
     */
    public void batchDelete(String pattern, int step) {
        while (scan(pattern, step).size() > 0) {
            Set<String> keys = scan(pattern, step);
            redisTemplate.delete(keys);
        }
    }

    /**
     * 模拟指定数量的数据
     *
     * @param count
     */
    public void mock(String keyPrefix, int count) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            map.put(keyPrefix + i, String.valueOf(i));
        }
        redisTemplate.opsForValue().multiSet(map);
    }


}
