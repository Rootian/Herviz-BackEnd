package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.VehicleClass;
import com.db.herviz.mapper.VehicleClassMapper;
import com.db.herviz.service.VehicleClassService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 17:14
 */
@Service
public class VehicleClassServiceImpl extends ServiceImpl<VehicleClassMapper, VehicleClass>
        implements VehicleClassService {

    @Override
    public VehicleClass getVehicleClassInfo(Long classId) {
        return getById(classId);
    }

    @Override
    public List<VehicleClass> getBatchVehicleClassInfo(List<Long> classIdList) {
        List<VehicleClass> infoList = new ArrayList<>();
        for (Long id : classIdList) {
            infoList.add(getVehicleClassInfo(id));
        }
        return infoList;
    }

    @Override
    public Double getRentalRate(Long classId) {
        VehicleClass vc = getById(classId);
        return vc.getRentalRate();
    }

    @Override
    public Double getRentalFee(Long classId) {
        VehicleClass vc = getById(classId);
        return vc.getFee();
    }
}
