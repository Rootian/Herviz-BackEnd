package com.db.herviz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Vehicle;
import com.db.herviz.entity.VehicleClass;
import com.db.herviz.service.RentalOrderService;
import com.db.herviz.service.VehicleClassService;
import com.db.herviz.service.VehicleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.util.*;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-20
 */
@RestController
@RequestMapping("/api/vehicle")
@Api
public class VehicleController {

    @Autowired
    private RentalOrderService orderService;

    @Autowired
    private VehicleClassService classService;

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchAvailableCar(@RequestBody String body) {
        JSONObject obj = JSONObject.parseObject(body);
        Long pickUpLoc = obj.getLong("pickUpLoc");
        Long pickUpDate = obj.getLong("pickUpDate");
        Long dropDate = obj.getLong("dropDate");

        List<Vehicle> carList = orderService.getAvailableCarInfo(pickUpLoc, pickUpDate, dropDate);
        Set<Long> classSet = new HashSet<>();
        for (Vehicle v : carList) {
            classSet.add(v.getClassId());
        }
        List<VehicleClass> classInfoList = classService.getBatchVehicleClassInfo(new ArrayList<>(classSet));
        return ResponseX.success(classInfoList);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getVehicleList(String keywords, Integer page, Integer limit) {
        Page<Vehicle> resultList = vehicleService.getVehicleList(keywords, page, limit);
        return ResponseX.page(resultList.getRecords(), resultList.getTotal());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateVehicle(@RequestBody String body) {
        Vehicle vehicle = JSONObject.parseObject(body, Vehicle.class);
        try {
            vehicleService.updateById(vehicle);
        } catch (Exception e) {
            return ResponseX.fail("update vehicle fail");
        }

        return ResponseX.success(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteVehicle(Long id) {
        try {
            vehicleService.removeById(id);
        } catch (Exception e) {
            return ResponseX.fail("Delete Fail");
        }

        return ResponseX.success(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addVehicle(@RequestBody String body) {
        Vehicle vehicle = JSONObject.parseObject(body, Vehicle.class);
        try {
            vehicleService.save(vehicle);
        } catch (Exception e) {
            return ResponseX.fail("add vehicle fail");
        }

        return ResponseX.success(null);
    }
}
