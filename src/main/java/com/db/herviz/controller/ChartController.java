package com.db.herviz.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.service.ChartService;
import io.swagger.annotations.Api;
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

    @GetMapping("/lastYearRevenue")
    @SaCheckLogin
    public String getLastYearRevenue() {
        return chartService.getLastYearRevenue();
    }

    @GetMapping("/allRevenue")
    public String getAllRevenue() {
        return ResponseX.success(chartService.getAllRevenue());
    }
}
