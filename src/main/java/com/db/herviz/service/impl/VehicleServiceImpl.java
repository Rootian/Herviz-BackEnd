package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Vehicle;
import com.db.herviz.mapper.VehicleMapper;
import com.db.herviz.service.RentalOrderService;
import com.db.herviz.service.VehicleService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/18 16:40
 */
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {


    @Override
    public List<Vehicle> getCarList(Long loc) {
        QueryWrapper<Vehicle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ofc_id", loc);
        return list(queryWrapper);
    }

    @Override
    public Page<Vehicle> getVehicleList(String keywords, Integer page, Integer limit) {
        QueryWrapper wrapper = new QueryWrapper();
        if (Strings.isNotBlank(keywords)) {
            // todo search keyword
        }
        Page<Vehicle> pages = new Page<>(page, limit);
        baseMapper.selectPage(pages, wrapper);
        return pages;
    }

}
