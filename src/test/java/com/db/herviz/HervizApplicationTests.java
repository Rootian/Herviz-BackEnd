package com.db.herviz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.db.herviz.domain.OrderStatusEnum;
import com.db.herviz.entity.*;
import com.db.herviz.mapper.ChartMapper;
import com.db.herviz.service.*;
import com.db.herviz.service.CouponCustService;
import com.db.herviz.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    @Resource
    private ChartService chartService;

    @Resource
    private ChartMapper chartMapper;

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

        //List<Map<String, Double>> lastYearRevenueByOffice = chartMapper.getLastYearRevenueByOffice();
        //System.out.println(lastYearRevenueByOffice);
        //String test = chartMapper.getTest(1l);
        //System.out.println(test);
        String lastYearRevenueByOffice = chartService.getLastYearRevenueByOffice();
        System.out.println(lastYearRevenueByOffice);
    }

    @Test
    public void test1() throws Exception{
        List<Long> orderIdList = new ArrayList<>();
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        List<Map<String, String>> test = chartMapper.getOrderNumByVehicleClass(startDate, startDate.plusYears(1), OrderStatusEnum.COMPLETED);
        System.out.println(test);
    }

    @Test
    public void test2() throws Exception{
        Integer rentCarNumCurrently = chartMapper.getRentCarNumCurrently();
        System.out.println(rentCarNumCurrently);
    }

}
