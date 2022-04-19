package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.VehicleClass;
import com.db.herviz.mapper.VehicleClassMapper;
import com.db.herviz.service.VehicleClassService;
import org.springframework.stereotype.Service;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 17:14
 */
@Service
public class VehicleClassServiceImpl extends ServiceImpl<VehicleClassMapper, VehicleClass>
        implements VehicleClassService {
}
