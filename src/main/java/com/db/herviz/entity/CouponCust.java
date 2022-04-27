package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 17:26
 */
@Data
@AllArgsConstructor
@TableName("zcq_coupon_cust")
public class CouponCust {

    private Long cId;

    private Long couponId;

    private String couponType;
}
