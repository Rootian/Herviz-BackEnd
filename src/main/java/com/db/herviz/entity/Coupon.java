package com.db.herviz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @JSONField(format = "yyyy-MM-dd")
    private Date sDate;

    @JSONField(format = "yyyy-MM-dd")
    private Date eDate;

    private Double discount;

    private String couponCode;

}
