package com.db.herviz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.herviz.domain.OrderStatusEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/5/4 22:08
 */
@Mapper
public interface ChartMapper extends BaseMapper {

    List<Map<String, Double>> getLastYearRevenueByOffice(@Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate,
                                                         @Param("status") OrderStatusEnum status);

    List<Map<String, String>> getOrderNumByVehicleClass(@Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate,
                                                         @Param("status") OrderStatusEnum status);

    Integer getRentCarNumCurrently();

    List<String> getTest(@Param("startDate") LocalDate startDate,
                   @Param("endDate") LocalDate endDate);
}
