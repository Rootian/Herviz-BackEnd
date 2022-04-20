package com.db.herviz.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Customer;
import com.db.herviz.entity.User;
import com.db.herviz.service.CustomerService;
import com.db.herviz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-17
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    /**
     * @Description User Register Request
     * @Author Rootian
     * @Date 2022-04-20
     * @param: register json parameter
     * @return java.lang.String
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody String body) {
        User user = JSON.parseObject(body, User.class);
        String md5Pw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pw);
        userService.save(user);
        return ResponseX.success(null);
    }


    /**
     * @Description User Login Request
     * @Author Rootian
     * @Date 2022-04-20
     * @param: login json parameter
     * @return java.lang.String
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String body) {
        JSONObject obj = JSONObject.parseObject(body);
        String username = obj.getString("username");
        String password = obj.getString("password");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userService.getOne(queryWrapper);

        if (Objects.isNull(user)) {
            return ResponseX.fail("username not found");
        }

        String md5Pw = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!user.getPassword().equals(md5Pw)) {
            return ResponseX.fail("incorrect password");
        }
        // construct session id
        String sessionId = String.valueOf(System.currentTimeMillis()) + "_" + String.valueOf(user.getId());

        StpUtil.login(sessionId);

        return ResponseX.success(StpUtil.getTokenInfo());
    }

    /**
     * @Description User Logout
     * @Author Rootian
     * @Date 2022-04-20
     * @param:
     * @return java.lang.String
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        StpUtil.logout();
        return ResponseX.success(null);
    }

}
