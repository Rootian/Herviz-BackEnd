package com.db.herviz.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-23
 */
@Service
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean saveList(String key, List<?> objList) {
        boolean result = false;
        try {
            if (redisTemplate.hasKey(key)) {
                // key exists, clear key
                while (redisTemplate.opsForList().size(key) > 0) {
                    redisTemplate.opsForList().leftPop(key);
                }
            }
            redisTemplate.opsForList().rightPushAll(key, objList);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<?> getList(String key) {
        if (!redisTemplate.hasKey(key))
            return new ArrayList<>();

        List<?> objList = redisTemplate.opsForList().range(key, 0, -1);
        return objList;
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void keySet() {

    }
}
