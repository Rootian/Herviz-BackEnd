package com.db.herviz.service;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/30 15:04
 */
public interface ChartService {

    public String getLastYearRevenueByMonth();

    String getLastYearOrderNumByMonth();

    String getLastYearRevenueByOffice();

    String getOrderNumByVehicleClass();

    String getPercentageOfRentAndAllCar();

    Double getAllRevenue();

    Integer getAllOrders();
}
