package com.db.herviz.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.RentalOrder;
import com.db.herviz.service.RentalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-21
 */
@RestController
@Api
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private RentalOrderService orderService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "saveOrder", notes = "")
    public String saveOrder(@RequestBody String body) {
        RentalOrder obj = JSONObject.parseObject(body, RentalOrder.class);
        try {
            orderService.saveOrder(obj);
        } catch (BusinessException e) {
            return ResponseX.fail(e.getMessage());
        }
        return ResponseX.success(null);
    }

    /**
     * @Description list order for customer
     * @Author Rootian
     * @Date 2022-04-25
     * @param: userId
     * @return java.lang.String
     */
    @RequestMapping(value = "/listForUser", method = RequestMethod.GET)
    public String listOrderForUser() {
        if (!StpUtil.isLogin()) {
            return ResponseX.fail("please log in first");
        }

        List<RentalOrder> orderList = orderService.listOrder();
        return ResponseX.success(orderList);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateOrder(@RequestBody String body) {
        RentalOrder order = JSONObject.parseObject(body, RentalOrder.class);
        try {
            orderService.updateById(order);
        } catch (Exception e) {
            return ResponseX.fail("update order fail");
        }

        return ResponseX.success(null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getOrderList(String keywords, Integer page, Integer limit) {
        Page<RentalOrder> orderList = orderService.getOrderList(keywords, page, limit);
        return ResponseX.page(orderList.getRecords(), orderList.getTotal());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteOrder(Long id) {
        try {
            orderService.removeById(id);
        } catch (Exception e) {
            return ResponseX.fail("Delete Fail");
        }

        return ResponseX.success(null);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.DELETE)
    public String cancelOrder(Long id) {
        orderService.cancelOrder(id);
        return ResponseX.success(null);
    }
}
