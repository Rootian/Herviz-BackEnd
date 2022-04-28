package com.db.herviz.schedule;

import com.db.herviz.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @Description: Scheduled Task to update cache
 * @Author Rootian
 * @Date 2022-04-27
 */
@Configuration
@EnableScheduling
public class CacheUpdateTask {

    @Autowired
    private RedisUtil redisUtil;

    @Scheduled(fixedRate=1000)
    private void updateCache() {

    }
}
