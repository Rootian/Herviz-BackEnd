package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 17:19
 */
@Data
@TableName("zcq_rental_order")
public class RentalOrder {

    @TableId("order_id")
    private Long id;

    private Date pDate;

    private Date dDate;

    private Long sOdometer;

    private Long eOdometer;

    private Long odometerLimit;

    private Long vin;

    private Long dropLoc;

    private Long pickupLoc;

    private Long invoId;

    private Long couponId;

    private Long cId;

}
