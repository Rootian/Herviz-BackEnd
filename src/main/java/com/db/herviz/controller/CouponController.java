package com.db.herviz.controller;

import com.alibaba.fastjson.JSON;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Coupon;
import com.db.herviz.service.CouponService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 15:31
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    @RequestMapping("/add")
    public String createCoupon(@RequestBody Coupon coupon) {
        try {
            couponService.save(coupon);
        } catch (BusinessException e) {
            return ResponseX.fail(e.getMessage());
        }

        return ResponseX.success(null);
    }
}
