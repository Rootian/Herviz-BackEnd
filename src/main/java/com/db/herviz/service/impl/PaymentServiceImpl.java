package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Payment;
import com.db.herviz.mapper.PaymentMapper;
import com.db.herviz.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:43
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
        implements PaymentService {
}
