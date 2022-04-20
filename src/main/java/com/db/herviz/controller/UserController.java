package com.db.herviz.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Customer;
import com.db.herviz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-20
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private CustomerService customerService;


    /**
     * @Description save or update user profile
     * @Author Rootian
     * @Date 2022-04-20
     * @param: profile json parameters
     * @return java.lang.String
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(@RequestBody String body) {
        Customer customer = JSONObject.parseObject(body, Customer.class);


        customerService.save(customer);
        return ResponseX.success(null);
    }
}
