package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.RentalOrder;
import com.db.herviz.mapper.RentalOrderMapper;
import com.db.herviz.service.RentalOrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:38
 */
@Service
public class RentalOrderServiceImpl extends ServiceImpl<RentalOrderMapper, RentalOrder>
        implements RentalOrderService {

    /**
     * @Description check every order of this car to see if the time duration is available
     * @Author Rootian
     * @Date 2022-04-20
     * @param: carId
     * @param: pickUpDate
     * @param: dropDate
     * @return boolean
     */
    @Override
    public boolean checkCarAvailable(Long carId, Long pickUpDate, Long dropDate) {

        // todo 时区问题
        QueryWrapper<RentalOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("vin", carId);
        List<RentalOrder> orderList = list(wrapper);
        for (RentalOrder order : orderList) {
            if ((order.getPDate().before(new Date(pickUpDate)) && order.getDDate().after(new Date(pickUpDate)))
                    || (order.getPDate().before(new Date(dropDate)) && order.getDDate().after(new Date(dropDate)))) {
                return false;
            }
        }
        return true;
    }
}
