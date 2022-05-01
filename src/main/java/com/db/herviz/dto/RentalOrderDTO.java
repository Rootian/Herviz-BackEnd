package com.db.herviz.dto;

import com.db.herviz.domain.OrderStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/27 20:45
 */
@Data
public class RentalOrderDTO {

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
