package com.db.herviz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/5/4 22:08
 */
@Mapper
public interface ChartMapper extends BaseMapper {

    Map<String, Double> getLastYearRevenueByOffice();
}
