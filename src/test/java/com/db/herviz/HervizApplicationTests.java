package com.db.herviz;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.db.herviz.entity.*;
import com.db.herviz.service.*;
import com.db.herviz.service.impl.CouponCustService;
import com.db.herviz.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest(classes = HervizApplication.class)
@Slf4j
class HervizApplicationTests {

    @Resource
    private CustomerServiceImpl customerService;

    @Resource
    private VehicleService vehicleService;

    @Resource
    private VehicleClassService vehicleClassService;

    @Resource
    private PaymentService paymentService;

    @Resource
    private CouponCustService couponCustService;

    @Resource
    private CouponService couponService;

    @Test
    void contextLoads() {
        LambdaQueryWrapper<Customer> qr = new LambdaQueryWrapper<>();

        Customer byId = customerService.getOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getId, "1"));
        log.info(byId.toString());

        Vehicle one = vehicleService.getOne(Wrappers.<Vehicle>lambdaQuery().eq(Vehicle::getId, 1));
        log.info(one.toString());

        VehicleClass vc = vehicleClassService.getOne(Wrappers.<VehicleClass>lambdaQuery().eq(VehicleClass::getId, 1));
        log.info(vc.toString());

        Payment payment = paymentService.getById(1);
        log.info(payment.toString());


    }

    @Test
    public void test() throws Exception{

        log.info(couponService.getCouponByUserId(1).toString());
    }

}
