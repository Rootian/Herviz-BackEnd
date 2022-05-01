package com.db.herviz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.db.herviz.entity.Payment;
import com.db.herviz.service.ChartService;
import com.db.herviz.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/30 15:04
 */
@Service
@Slf4j
public class ChartServiceImpl implements ChartService {

    @Resource
    private PaymentService paymentService;

    @Override
    public String getLastYearRevenue() {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        List<Payment> payments = paymentService.list(Wrappers.<Payment>lambdaQuery()
                .between(Payment::getPayDate, startDate, startDate.plusYears(1)));

        SortedMap<String, Double> monthRevenueMap = new TreeMap<>();
        for (int i = 0; i < 12; i++) {
            monthRevenueMap.put(startDate.format(DateTimeFormatter.ofPattern("YYYY-MM")), 0d);
            startDate = startDate.plusMonths(1);
        }
        log.info(payments.toString());
        for (Payment payment : payments) {
            String month = new SimpleDateFormat("YYYY-MM").format(payment.getPayDate());
            monthRevenueMap.put(month, monthRevenueMap.get(month) + payment.getAmount());
        }

        JSONObject res = new JSONObject();
        res.put("title", new LinkedList<>(monthRevenueMap.keySet()));
        res.put("data", new LinkedList<>(monthRevenueMap.values()));

        return res.toJSONString();
    }
}
