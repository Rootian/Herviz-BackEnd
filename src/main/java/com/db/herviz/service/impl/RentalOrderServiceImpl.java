package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.RentalOrder;
import com.db.herviz.mapper.RentalOrderMapper;
import com.db.herviz.service.RentalOrderService;
import org.springframework.stereotype.Service;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:38
 */
@Service
public class RentalOrderServiceImpl extends ServiceImpl<RentalOrderMapper, RentalOrder>
        implements RentalOrderService {
}
