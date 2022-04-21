package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/18 17:09
 */
@Data
@TableName("zcq_vehicle_class")
public class VehicleClass {

    @TableId(value = "class_id")
    private Long id;

    @TableField("class_type")
    private String type;

    private Double rentalRate;

    private Double fee;

    private Integer seat;

    private Integer bag;

}
