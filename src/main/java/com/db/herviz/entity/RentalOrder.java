package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.db.herviz.domain.OrderStatusEnum;
import lombok.Data;

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

    private Date pDate;

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

}
