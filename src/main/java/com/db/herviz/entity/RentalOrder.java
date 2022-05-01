package com.db.herviz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.db.herviz.domain.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 17:19
 */
@Data
@TableName("zcq_rental_order")
public class RentalOrder {

    @TableId(value = "order_id", type = IdType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date pDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date dDate;

    private Long sOdometer;

    private Long eOdometer;

    private Long odometerLimit;

    private Long vin;

    private Long dropLoc;

    private Long pickupLoc;

    private Long couponId;

    private Long uId;

    private Long classId;

    private OrderStatusEnum status;

    private Date createTime;

}
