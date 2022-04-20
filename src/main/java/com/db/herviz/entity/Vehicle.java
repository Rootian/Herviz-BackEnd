package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/17 18:39
 */
@Data
@TableName("zcq_vehicle")
public class Vehicle {

    @TableId("vin")
    private Long id;

    private String make;

    private String model;

    private Long year;

    private Long lpn;

    private Long classId;

    private Long curLoc;

    private Long ofcId;


}
