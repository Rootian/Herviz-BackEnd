package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.domain.OrderStatusEnum;
import com.db.herviz.entity.RentalOrder;
import com.db.herviz.entity.Vehicle;

import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:37
 */
public interface RentalOrderService extends IService<RentalOrder> {

    List<Vehicle> getAvailableCarInfo(Long pickUpLoc, Long pickUpDate, Long dropDate);

    boolean checkCarAvailable(Long carId, Long pickUpDate, Long dropDate);

    void saveOrder(RentalOrder order);

    List<RentalOrder> listOrder(Long userId);

    void setOrderStatus(Long orderId, OrderStatusEnum status);
}
