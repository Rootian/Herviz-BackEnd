package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.entity.RentalOrder;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:37
 */
public interface RentalOrderService extends IService<RentalOrder> {

    boolean checkCarAvailable(Long carId, Long pickUpDate, Long dropDate);
}
