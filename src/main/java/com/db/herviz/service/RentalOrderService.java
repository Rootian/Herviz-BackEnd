package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    List<RentalOrder> listOrder();

    void setOrderStatus(Long orderId, OrderStatusEnum status);

    void cancelOrder(Long id);

    Page<RentalOrder> getOrderList(String keywords, Integer page, Integer limit);

    void update(RentalOrder order);
}
