package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Coupon;
import com.db.herviz.entity.CouponCust;
import com.db.herviz.entity.Customer;
import com.db.herviz.mapper.CouponMapper;
import com.db.herviz.service.CouponService;
import com.db.herviz.service.CustomerService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 15:06
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon>
        implements CouponService {

    @Resource
    CustomerService customerService;

    @Resource
    CouponCustService couponCustService;


    @Override
    public List<Coupon> getCouponByUserId(int userId) {
        Customer customer = customerService.getCustomerByUId(userId);
        List<CouponCust> cclist = couponCustService.list(Wrappers.<CouponCust>lambdaQuery()
                .eq(CouponCust::getCId, customer.getId()));
        List<Long> ids = new LinkedList<>();
        for (CouponCust couponCust : cclist) {
            ids.add(couponCust.getCouponId());
        }
        return listByIds(ids);
    }
}
