package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Vehicle;
import com.db.herviz.mapper.VehicleMapper;
import com.db.herviz.service.VehicleService;
import org.springframework.stereotype.Service;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/18 16:40
 */
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {
}
