package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/25 15:00
 */
@Data
@TableName("zcq_coupon")
public class Coupon {

    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Long id;

    private Date sDate;

    private Date eDate;

    private Double discount;

    private String couponCode;

}
