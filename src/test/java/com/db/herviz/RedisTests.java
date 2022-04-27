package com.db.herviz;

import com.db.herviz.entity.User;
import com.db.herviz.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-23
 */
@SpringBootTest(classes = HervizApplication.class)
@Slf4j
public class RedisTests {

    @Autowired
    RedisUtil redisUtil;

    @Test
    void testRedis() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1l);
        user1.setUsername("aaron");
        user1.setPassword("000");
        User user2 = new User();
        user2.setId(2l);
        user2.setUsername("wc");
        user2.setPassword("111");

        userList.add(user1);
        userList.add(user2);

        redisUtil.saveList("userList", userList);
        List<User> result = (List<User>)redisUtil.getList("userList");
        for (User user : result) {
            System.out.println(user.getUsername());
        }


    }
}
