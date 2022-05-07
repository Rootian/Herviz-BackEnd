package com.db.herviz.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.service.ChartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/30 14:59
 */
@RestController
@Api(value = "统计表")
@RequestMapping("/api/chart")
public class ChartController {

    @Resource
    private ChartService chartService;
    @ApiOperation("获取最近十二个月每月总收入")
    @GetMapping("/lastYearRevenueByMonth")
    public String getLastYearRevenue() {
        return chartService.getLastYearRevenueByMonth();
    }

    @GetMapping("/lastYearOrderNumByMonth")
    @ApiOperation("获取最近十二个月每月总订单数量")
    public String getLastYearOrderNumByMonth() {
        return chartService.getLastYearOrderNumByMonth();
    }

    @GetMapping("/lastYearRevenueByOffice")
    @ApiOperation("获取不同Office最近十二个月收入")
    public String getLastYearRevenueByOffice() {
        return chartService.getLastYearRevenueByOffice();
    }

    @GetMapping("/getOrderNumByVehicleClass")
    @ApiOperation("获取不同vehicleClass最近十二个月订单数量")
    public String getOrderNumByVehicleClass() {
        return chartService.getOrderNumByVehicleClass();
    }

    @GetMapping("/percentageOfRentAndAllCar")
    @ApiOperation("获取目前系统已租出车辆")
    public String getPercentageOfRentAndAllCar() {
        return chartService.getPercentageOfRentAndAllCar();
    }

    @GetMapping("/allRevenue")
    public String getAllRevenue() {
        return ResponseX.success(chartService.getAllRevenue());
    }

    @GetMapping("/allOrders")
    public String getAllOrders() { return ResponseX.success(chartService.getAllOrders());}
}
