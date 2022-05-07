package com.db.herviz.controller;

import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.VehicleClass;
import com.db.herviz.service.VehicleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-05-06
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private VehicleClassService classService;

    /**
     * @Description bury point when user visit any vehicle class
     * @Author Rootian
     * @Date 2022-05-06
     * @param:
     * @return java.lang.String
     */
    @GetMapping("/visitClass")
    public String buryPointClassVisit(Long classId) {
        VehicleClass vClass = classService.getById(classId);
        vClass.buryVisit();
        classService.updateById(vClass);
        return ResponseX.success(null);
    }
}
