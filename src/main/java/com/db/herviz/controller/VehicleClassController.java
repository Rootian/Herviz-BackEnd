package com.db.herviz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.VehicleClass;
import com.db.herviz.service.VehicleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-27
 */
@RestController
@RequestMapping("/api/vehicleClass")
public class VehicleClassController {

    @Autowired
    private VehicleClassService classService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getClassList(String keywords, Integer page, Integer limit) {
        Page<VehicleClass> classList = classService.getClassList(keywords, page, limit);
        return ResponseX.page(classList.getRecords(), classList.getTotal());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateVehicle(@RequestBody String body) {
        VehicleClass vehicleClass = JSONObject.parseObject(body, VehicleClass.class);
        try {
            classService.updateById(vehicleClass);
        } catch (Exception e) {
            return ResponseX.fail("update vehicle class fail");
        }

        return ResponseX.success(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteVehicle(Long id) {
        try {
            classService.removeById(id);
        } catch (Exception e) {
            return ResponseX.fail("Delete Fail");
        }

        return ResponseX.success(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addVehicle(@RequestBody String body) {
        VehicleClass vehicle = JSONObject.parseObject(body, VehicleClass.class);
        try {
            classService.save(vehicle);
        } catch (Exception e) {
            return ResponseX.fail("add vehicle Class fail");
        }

        return ResponseX.success(null);
    }


}
