package com.db.herviz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/20 15:38
 */
@Data
@AllArgsConstructor
public class VehicleSearchDTO {

    private Date pDate;

    private Date dDate;

}
