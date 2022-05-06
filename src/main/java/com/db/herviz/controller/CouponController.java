package com.db.herviz.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Coupon;
import com.db.herviz.entity.Customer;
import com.db.herviz.service.CouponService;
import com.db.herviz.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 15:31
 */
@RestController
@Slf4j
@Api
@RequestMapping("/api/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    @Resource
    private CustomerService customerService;

    @PostMapping("/add")
    @ApiOperation(value = "创建优惠券", notes = "{\n" +
            "    \"sDate\": \"2022-05-25\",\n" +
            "    \"eDate\": \"2022-06-26\",\n" +
            "    \"discount\": \"0.35\"\n" +
            "}")
    public String createCoupon(@RequestBody String body) {
        Coupon coupon = JSON.parseObject(body, Coupon.class);
        coupon.setCouponCode(RandomStringUtils.randomAlphabetic(6).toUpperCase());
        try {
            couponService.save(coupon);
        } catch (BusinessException e) {
            return ResponseX.fail(e.getMessage());
        }

        return ResponseX.success(null);
    }

    @PostMapping("/update")
    public String updateCoupon(@RequestBody String body) {
        Coupon coupon = JSONObject.parseObject(body, Coupon.class);
        try {
            couponService.updateById(coupon);
        } catch (Exception e) {
            ResponseX.fail(e.getMessage());
        }
        return ResponseX.success(null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getOfficeList(String keywords, Integer page, Integer limit) {
        Page<Coupon> officeList = couponService.getCouponList(keywords, page, limit);
        return ResponseX.page(officeList.getRecords(), officeList.getTotal());
    }

    @GetMapping("/getByUserId")
    @ApiOperation(value = "查询用户优惠券")
    public String getCouponByUserId() {
        List<Coupon> couponByUserId;
        try {
            String sessionId = StpUtil.getLoginIdAsString();
            Long userId = Long.valueOf(sessionId.split("_")[1]);

            couponByUserId = couponService.getCouponByUserId(userId);
        } catch (BusinessException e) {
            return ResponseX.fail(e.getMessage());
        }

        return ResponseX.success(couponByUserId);

    }

    @GetMapping("/addCouponToAccount")
    @ApiOperation(value = "用户添加优惠券")
    public String addCouponToAccount(String couponCode) {
        Coupon saveResult;
        String sessionId = StpUtil.getLoginIdAsString();
        Long userId = Long.valueOf(sessionId.split("_")[1]);
        try {
            saveResult = couponService.addCouponToAccount(userId, couponCode);
        } catch (BusinessException e){
            return ResponseX.fail(e.getMessage());
        }
        return ResponseX.success(saveResult);
    }




}
