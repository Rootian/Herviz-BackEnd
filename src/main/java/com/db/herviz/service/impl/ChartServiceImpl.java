package com.db.herviz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.db.herviz.domain.OrderStatusEnum;
import com.db.herviz.entity.Invoice;
import com.db.herviz.entity.Payment;
import com.db.herviz.entity.RentalOrder;
import com.db.herviz.mapper.ChartMapper;
import com.db.herviz.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/30 15:04
 */
@Service
@Slf4j
public class ChartServiceImpl implements ChartService {

    @Resource
    private PaymentService paymentService;

    @Resource
    private RentalOrderService rentalOrderService;

    @Resource
    private VehicleService vehicleService;

    @Resource
    private InvoiceService invoiceService;

    @Resource
    private ChartMapper chartMapper;

    @Override
    public String getLastYearRevenueByMonth() {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        List<Payment> payments = paymentService.list(Wrappers.<Payment>lambdaQuery()
                .between(Payment::getPayDate, startDate, startDate.plusYears(1)));

        SortedMap<String, Double> monthRevenueMap = new TreeMap<>();
        for (int i = 0; i < 12; i++) {
            monthRevenueMap.put(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM")), 0d);
            startDate = startDate.plusMonths(1);
        }
        log.info(payments.toString());
        for (Payment payment : payments) {
            String month = new SimpleDateFormat("yyyy-MM").format(payment.getPayDate());
            monthRevenueMap.put(month, monthRevenueMap.get(month) + payment.getAmount());
        }

        JSONObject res = new JSONObject();
        res.put("title", new LinkedList<>(monthRevenueMap.keySet()));
        res.put("data", new LinkedList<>(monthRevenueMap.values()));

        return res.toJSONString();
    }

    @Override
    public String getLastYearOrderNumByMonth() {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        List<RentalOrder> orderList = rentalOrderService.list(Wrappers.<RentalOrder>lambdaQuery()
                .between(RentalOrder::getCreateTime, startDate, startDate.plusYears(1))
                .eq(RentalOrder::getStatus, OrderStatusEnum.COMPLETED));
        SortedMap<String, Integer> monthOderNumMap = new TreeMap<>();
        for (int i = 0; i < 12; i++) {
            monthOderNumMap.put(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM")), 0);
            startDate = startDate.plusMonths(1);
        }
        for (RentalOrder rentalOrder : orderList) {
            String month = new SimpleDateFormat("yyyy-MM").format(rentalOrder.getCreateTime());
            monthOderNumMap.put(month, monthOderNumMap.get(month) + 1);
        }

        JSONObject res = new JSONObject();
        res.put("title", new LinkedList<>(monthOderNumMap.keySet()));
        res.put("data", new LinkedList<>(monthOderNumMap.values()));
        return res.toString();
    }

    @Override
    public String getLastYearRevenueByOffice() {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        List<Map<String, Double>> officeRevenueList =
                chartMapper.getLastYearRevenueByOffice(startDate, startDate.plusYears(1), OrderStatusEnum.COMPLETED);
        JSONObject res = new JSONObject();
        res.put("data", officeRevenueList);
        return res.toJSONString();
    }

    @Override
    public String getOrderNumByVehicleClass() {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        List<Map<String, String>> officeRevenueList =
                chartMapper.getOrderNumByVehicleClass(startDate, startDate.plusYears(1), OrderStatusEnum.COMPLETED);
        JSONObject res = new JSONObject();
        res.put("data", officeRevenueList);
        return res.toJSONString();
    }

    @Override
    public String getPercentageOfRentAndAllCar() {
        double rentCarNumCurrently = (double) chartMapper.getRentCarNumCurrently();
        int allCarCount = vehicleService.count();
        double res = (rentCarNumCurrently / allCarCount);
        return String.format("%.2f", res);
    }

    @Override
    public Double getAllRevenue() {
        double sum = 0;
        List<Payment> payList = paymentService.list();
        for (Payment pay : payList) {
            sum += pay.getAmount();
        }
        return sum;
    }
}
