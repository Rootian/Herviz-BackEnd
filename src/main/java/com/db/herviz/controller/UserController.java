package com.db.herviz.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Customer;
import com.db.herviz.entity.User;
import com.db.herviz.service.CustomerService;
import com.db.herviz.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-20
 */
@RestController
@RequestMapping(value = "/api/user")
@Api
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;


    /**
     * @Description save or update user profile
     * @Author Rootian
     * @Date 2022-04-20
     * @param: profile json parameters
     * @return java.lang.String
     */
    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    @SaCheckLogin
    public String saveProfile(@RequestBody String body) {
        Customer customer = JSONObject.parseObject(body, Customer.class);
        String sessionId = StpUtil.getLoginIdAsString();
        Long uId = Long.valueOf(sessionId.split("_")[1]);
        QueryWrapper wrapper = new QueryWrapper();
        try {
            wrapper.eq("u_id", uId);
            customerService.update(customer, wrapper);
        } catch (Exception e) {
            return ResponseX.fail("update fail");
        }
        return ResponseX.success(null);
    }

    @GetMapping("/getProfile")
    public String getUserProfile() {
        String sessionId = StpUtil.getLoginIdAsString();
        Long uId = Long.valueOf(sessionId.split("_")[1]);

        Customer customer = customerService.getCustomerByUId(uId);
        return ResponseX.success(customer);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUserList(String keywords, Integer page, Integer limit) {
        Page<User> userList = userService.getUserList(keywords, page, limit);
        return ResponseX.page(userList.getRecords(), userList.getTotal());
    }

}
