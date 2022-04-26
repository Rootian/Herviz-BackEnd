package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.entity.Coupon;

import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 15:06
 */
public interface CouponService extends IService<Coupon> {

    public List<Coupon> getCouponByUserId (int userId);

}
