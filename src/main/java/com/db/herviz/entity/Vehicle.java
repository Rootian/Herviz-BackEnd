package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/17 18:39
 */
@Data
@TableName("zcq_vehicle")
public class Vehicle implements Serializable {

    @TableId(value = "vin", type = IdType.AUTO)
    private Long id;

    private String make;

    private String model;

    private Long year;

    private Long lpn;

    private Long classId;

    private Long curLoc;

    private Long ofcId;


}
