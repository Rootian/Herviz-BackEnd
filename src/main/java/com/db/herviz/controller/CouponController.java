package com.db.herviz.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Coupon;
import com.db.herviz.entity.Customer;
import com.db.herviz.service.CouponService;
import com.db.herviz.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 15:31
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    @Resource
    private CustomerService customerService;

    @PostMapping("/add")
    public String createCoupon(@RequestBody Coupon coupon) {
        try {
            couponService.save(coupon);
        } catch (BusinessException e) {
            return ResponseX.fail(e.getMessage());
        }

        return ResponseX.success(null);
    }

    @GetMapping("/getByUserId")
    public String getCouponByUserId(int userId) {
        List<Coupon> couponByUserId;
        try {
            couponByUserId = couponService.getCouponByUserId(userId);
        } catch (BusinessException e) {
            return ResponseX.fail(e.getMessage());
        }

        return ResponseX.success(couponByUserId);

    }




}
