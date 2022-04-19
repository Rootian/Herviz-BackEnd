package com.db.herviz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.herviz.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:43
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
