package com.db.herviz.controller;

import com.alibaba.fastjson.JSONObject;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listOrder(Long userId) {
        List<RentalOrder> orderList = orderService.listOrder(userId);
        return ResponseX.success(orderList);
    }
}
