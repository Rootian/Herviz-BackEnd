package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/17 16:35
 */
@Data
@TableName("zcq_customer")
public class Customer {

    @TableId(value = "c_id", type = IdType.AUTO)
    private Long id;

    @TableField("c_type")
    private String type;

    private String firstName;

    private String lastName;

    private String email;

    private Integer phoneNo;

    private String address;

    private String country;

    private String state;

    private String city;

    private String zipCode;

    private Long uId;

    private Long dln;

    private String insrcCompany;

    private Long insrcNo;

    private Long corpId;


}
