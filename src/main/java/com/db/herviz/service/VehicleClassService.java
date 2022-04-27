package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.entity.VehicleClass;

import java.util.List;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/18 17:13
 */
public interface VehicleClassService extends IService<VehicleClass> {

    /**
     * @Description get single class information by id
     * @Author Rootian
     * @Date 2022-04-21
     * @param: classId
     * @return com.db.herviz.entity.VehicleClass
     */
    VehicleClass getVehicleClassInfo(Long classId);

    /**
     * @Description get list of class info by id list
     * @Author Rootian
     * @Date 2022-04-21
     * @param: classIdList
     * @return java.util.List<com.db.herviz.entity.VehicleClass>
     */
    List<VehicleClass> getBatchVehicleClassInfo(List<Long> classIdList);

    Double getRentalRate(Long classId);

    Double getRentalFee(Long classId);

    Page<VehicleClass> getClassList(String keywords, Integer page, Integer limit);
}
