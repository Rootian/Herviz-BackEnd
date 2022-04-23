package com.db.herviz.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.CacheFindList;
import com.db.herviz.domain.OrderStatusEnum;
import com.db.herviz.entity.Invoice;
import com.db.herviz.entity.RentalOrder;
import com.db.herviz.entity.Vehicle;
import com.db.herviz.mapper.RentalOrderMapper;
import com.db.herviz.redis.RedisUtil;
import com.db.herviz.service.InvoiceService;
import com.db.herviz.service.RentalOrderService;
import com.db.herviz.service.VehicleClassService;
import com.db.herviz.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:38
 */
@Service
public class RentalOrderServiceImpl extends ServiceImpl<RentalOrderMapper, RentalOrder>
        implements RentalOrderService {


    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private VehicleClassService classService;

    @Override
    @CacheFindList
    public List<Vehicle> getAvailableCarInfo(Long pickUpLoc, Long pickUpDate, Long dropDate) {


        // get the list of all cars from pickup location
        List<Vehicle> carList = vehicleService.getCarList(pickUpLoc);
        List<Vehicle> availableList = new ArrayList<>();
        for (Vehicle car : carList) {
            // check if available for each car
            if (checkCurrLocAvailable(car) &&
                    checkCarAvailable(car.getId(), pickUpDate, dropDate)) {
                availableList.add(car);
            }

        }
        return availableList;
    }

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
        wrapper.ge("d_date", new Date());
        List<RentalOrder> orderList = list(wrapper);

        for (RentalOrder order : orderList) {
            if (Math.max(order.getPDate().getTime(), pickUpDate) < Math.min(order.getDDate().getTime(), dropDate)) {
                return false;
            }
//            if ((order.getPDate().before(new Date(pickUpDate)) && order.getDDate().after(new Date(pickUpDate)))
//                    || (order.getPDate().before(new Date(dropDate)) && order.getDDate().after(new Date(dropDate)))) {
//                return false;
//            }
        }
        return true;
    }

    /**
     * @Description create order and generate invoice
     * @Author Rootian
     * @Date 2022-04-21
     * @param: order
     * @return void
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveOrder(RentalOrder order) {

        //todo 缓存用户界面金额，防止实际计算付款金额不一致
        Long vehicleId = assignVehicle(order);
        if (vehicleId < 0)
            throw new BusinessException("out of stock");
        order.setVin(vehicleId);
        order.setStatus(OrderStatusEnum.CREATED);

        // save order
        if (!save(order))
            throw new BusinessException("save order fail");

        // generate invoice
        Invoice invoice = new Invoice();
        invoice.setDate(new Date());
        invoice.setOrderId(order.getId());
        invoice.setAmount(calOrderAmount(order));
        if (!invoiceService.save(invoice))
            throw new BusinessException("generate invoice fail");

    }

    @Override
    public List<RentalOrder> listOrder(Long userId) {
        QueryWrapper<RentalOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id", userId);
        return list(wrapper);
    }

    /**
     * @Description change order status
     * @Author Rootian
     * @Date 2022-04-22
     * @param: orderId
     * @param: status
     * @return void
     */
    @Override
    public void setOrderStatus(Long orderId, OrderStatusEnum status) {
        RentalOrder order = getById(orderId);
        order.setStatus(status);
        updateById(order);
    }

    /**
     * @Description assign vehicle of specific class
     * @Author Rootian
     * @Date 2022-04-21
     * @param: order
     * @return java.lang.Long
     */
    private Long assignVehicle(RentalOrder order) {
        // assign car
        List<Vehicle> carList = getAvailableCarInfo(order.getPickupLoc(),
                order.getPDate().getTime(), order.getDDate().getTime());
        // out of stock
        if (carList.size() == 0)
            return -1l;
        // assign first available car
        for (Vehicle car : carList) {
            if (car.getClassId().equals(order.getClassId()))
                return carList.get(0).getId();
        }
        return -1l;
    }


    /**
     * @Description calculate money for the order
     * @Author Rootian
     * @Date 2022-04-21
     * @param: order
     * @return java.lang.Double
     */
    private Double calOrderAmount(RentalOrder order) {
        Double rentalRate = classService.getRentalRate(order.getClassId());
        long numOfRent = (order.getDDate().getTime() - order.getPDate().getTime()) / (24 * 100 * 3600);
        return rentalRate * numOfRent;

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
