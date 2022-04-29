package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Coupon;
import com.db.herviz.entity.CouponCust;

import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 15:06
 */
public interface CouponService extends IService<Coupon> {

    List<Coupon> getCouponByUserId (Long userId);

    /**
     * add coupon to account
     * @param userId
     * @param couponCode
     */
    boolean addCouponToAccount (Long userId, String couponCode);

    /**
     * check couponCode validation
     * @param couponCode
     * @return coupon
     */
    Coupon checkCouponValidation (String couponCode);

    Page<Coupon> getCouponList(String keywords, Integer page, Integer limit);
}
