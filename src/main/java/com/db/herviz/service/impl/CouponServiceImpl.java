package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.entity.Coupon;
import com.db.herviz.entity.CouponCust;
import com.db.herviz.entity.Customer;
import com.db.herviz.mapper.CouponMapper;
import com.db.herviz.service.CouponCustService;
import com.db.herviz.service.CouponService;
import com.db.herviz.service.CustomerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Coupon> getCouponByUserId(Long userId) {
        Customer customer = customerService.getCustomerByUId(userId);
        List<CouponCust> cclist = couponCustService.list(Wrappers.<CouponCust>lambdaQuery()
                .eq(CouponCust::getCId, customer.getId()));
        List<Long> ids = new LinkedList<>();
        for (CouponCust couponCust : cclist) {
            ids.add(couponCust.getCouponId());
        }
        List<Coupon> couponResult = new ArrayList<>();
        if (!ids.isEmpty()) {
            couponResult = listByIds(ids);
            //remove the expired coupons
            couponResult.removeIf(coupon -> coupon.getSDate() != null && coupon.getEDate() != null &&
                    !(new Date().after(coupon.getSDate()) && new Date().before(coupon.getEDate())));
        }
        return couponResult;
    }

    @Override
    public Coupon addCouponToAccount(Long userId, String couponCode) throws BusinessException {
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

        if (coupon.getSDate() != null && coupon.getEDate() != null &&
                !(new Date().after(coupon.getSDate()) && new Date().before(coupon.getEDate()))) {
            throw new BusinessException("Coupon has expired");
        }
        return coupon;
    }

    @Override
    public Page<Coupon> getCouponList(String keywords, Integer page, Integer limit) {
        QueryWrapper<Coupon> wrapper = new QueryWrapper<>();
        if (Strings.isNotEmpty(keywords)) {
            // search keywords
        }
        Page<Coupon> pages = new Page<>(page, limit);
        baseMapper.selectPage(pages, wrapper);
        return pages;
    }
}
