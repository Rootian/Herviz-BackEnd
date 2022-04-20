package com.db.herviz.controller;

import com.alibaba.fastjson.JSONObject;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-20
 */
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String searchAvailableCar(@RequestBody String body) {
        JSONObject obj = JSONObject.parseObject(body);
        Long pickUpLoc = obj.getLong("pickUpLoc");
        Long pickUpDate = obj.getLong("pickUpDate");
        Long dropDate = obj.getLong("dropDate");

        List<Long> carList = vehicleService.getAvailableCarInfo(pickUpLoc, pickUpDate, dropDate);
        return ResponseX.success(carList);
    }
}
