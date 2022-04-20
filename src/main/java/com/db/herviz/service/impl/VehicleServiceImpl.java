package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Vehicle;
import com.db.herviz.mapper.VehicleMapper;
import com.db.herviz.service.RentalOrderService;
import com.db.herviz.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/18 16:40
 */
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {

    @Autowired
    private RentalOrderService orderService;


    @Override
    public List<Long> getAvailableCarInfo(Long pickUpLoc, Long pickUpDate, Long dropDate) {
        // get the list of all cars from pickup location
        List<Vehicle> carList = getCarList(pickUpLoc);
        List<Long> availableList = new ArrayList<>();
        for (Vehicle car : carList) {
            // check if available for each car
            if (checkCurrLocAvailable(car) &&
                    orderService.checkCarAvailable(car.getId(), pickUpDate, dropDate)) {
                    availableList.add(car.getId());
            }

        }
        return availableList;
    }

    private List<Vehicle> getCarList(Long loc) {
        QueryWrapper<Vehicle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ofc_id", loc);
        return list(queryWrapper);
    }


    /**
     * @Description check if the current location of this car
     *              is the same as the belonging of this car
     * @Author Rootian
     * @Date 2022-04-20
     * @param: carId
     * @param: loc
     * @return boolean
     */
    private boolean checkCurrLocAvailable(Vehicle car) {
        return car.getCurLoc().equals(car.getOfcId());
    }
}
