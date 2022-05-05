package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.CouponCust;
import com.db.herviz.mapper.CouponCustMapper;
import com.db.herviz.service.CouponCustService;
import org.springframework.stereotype.Service;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 19:49
 */
@Service
public class CouponCustServiceImpl extends ServiceImpl<CouponCustMapper, CouponCust>
        implements CouponCustService {
}
