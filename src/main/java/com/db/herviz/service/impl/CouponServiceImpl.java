package com.db.herviz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Coupon;
import com.db.herviz.entity.CouponCust;
import com.db.herviz.entity.Customer;
import com.db.herviz.mapper.CouponMapper;
import com.db.herviz.service.CouponService;
import com.db.herviz.service.CustomerService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
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
        List<Coupon> couponResult = listByIds(ids);
        //remove the expired coupons
        couponResult.removeIf(coupon -> !(new Date().after(coupon.getSDate()) && new Date().before(coupon.getEDate())));
        return couponResult;
    }

    @Override
    public Coupon addCouponToAccount(int userId, String couponCode) throws BusinessException {
        Customer customer = customerService.getCustomerByUId(userId);
        Coupon couponToAdd = checkCouponValidation(couponCode);
        List<Coupon> couponList = getCouponByUserId(userId);
        if (!couponList.isEmpty()){
            for (Coupon coupon : couponList) {
                if(couponCode.equals(coupon.getCouponCode())) {
                    throw new BusinessException("You already have this coupon");
                }
            }
        }
        CouponCust couponCust = new CouponCust(customer.getId(),couponToAdd.getId(), customer.getType());
        if (!couponCustService.save(couponCust)) {
            throw new BusinessException("Save coupon failed");
        }
        return couponToAdd;
    }

    @Override
    public Coupon checkCouponValidation(String couponCode) {
        Coupon coupon = getOne(Wrappers.<Coupon>lambdaQuery().eq(Coupon::getCouponCode, couponCode));
        if (coupon == null) {
            throw new BusinessException("Coupon does not exist");
        }

        if (!(new Date().after(coupon.getSDate()) && new Date().before(coupon.getEDate()))) {
            throw new BusinessException("Coupon has expired");
        }
        return coupon;
    }
}
