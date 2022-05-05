package com.db.herviz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.db.herviz.domain.OrderStatusEnum;
import com.db.herviz.entity.Invoice;
import com.db.herviz.entity.Payment;
import com.db.herviz.entity.RentalOrder;
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
    private OfficeService officeService;

    @Resource
    private InvoiceService invoiceService;

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
        TreeMap<String, Double> officeRevenueMap = new TreeMap<>();
        List<Long> orderIdList = new ArrayList<>();
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        // List order in last one year
        List<RentalOrder> orderList = rentalOrderService.list(Wrappers.<RentalOrder>lambdaQuery()
                .between(RentalOrder::getCreateTime, startDate, startDate.plusYears(1))
                .eq(RentalOrder::getStatus, OrderStatusEnum.COMPLETED));
        // Order Id list for listing according invoices
        orderList.forEach(order -> {
            orderIdList.add(order.getId());
        });
        List<Invoice> invoiceList = invoiceService.list(Wrappers.<Invoice>lambdaQuery().in(Invoice::getOrderId, orderIdList));
        // Map key: orderId value: order total
        HashMap<Long, Double> orderTotalMap = new HashMap<>();
        invoiceList.forEach(invoice -> {
            orderTotalMap.put(invoice.getOrderId(), invoice.getAmount());
        });
        orderList.forEach(order -> {

        });
        log.info(orderTotalMap.toString());
        return null;
    }
}
